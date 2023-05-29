/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointments;
import Model.BookedAppointments;
import View.ViewManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PatientDashbordFreeAppoitmentsController implements Initializable {

    @FXML
    private Button FreeAppoitmentsPage;
    @FXML
    private Button BokkedAppoitmentsPage;
    @FXML
    private Pane LogOut;
    @FXML
    private Button BokkedButton;
    @FXML
    private TableView<Appointments> AppoitmentsTable;
    @FXML
    private TableColumn<Appointments, Integer> idcolumn;
    @FXML
    private TableColumn<Appointments, String> datecolumn;
    @FXML
    private TableColumn<Appointments, String> daycolumn;
    @FXML
    private TableColumn<Appointments, String> timecolumn;
    @FXML
    private Button logoutbutton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");
    EntityManager entity = entityMF.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.idcolumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.datecolumn.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        this.daycolumn.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        this.timecolumn.setCellValueFactory(new PropertyValueFactory("appointmentTime"));

        List<Appointments> freeAppointments = new AppointmentsJpaController(entityMF).findFreeAppointmentsEntities();
        AppoitmentsTable.getItems().clear();
        AppoitmentsTable.getItems().addAll(freeAppointments);
    }

    @FXML
    private void FreeAppoitmentsButton(ActionEvent event) {
        ViewManager.patientDashBordStage.changeSceneToPatientDashbordFreeappointments();

    }

    @FXML
    private void BokkedAppoitmentsButton(ActionEvent event) {
        ViewManager.patientDashBordStage.changeSceneToPatientDashbordBokkedappointments();
    }

    @FXML
private void BokkedAction(ActionEvent event) {
    Appointments selectedAppointment = AppoitmentsTable.getSelectionModel().getSelectedItem();

    if (selectedAppointment != null) {
        int appointmentId = selectedAppointment.getId();  
        int userId = UserController.getLoggedInUserId();

        AppointmentsJpaController controller = new AppointmentsJpaController(entityMF);
        BookedAppointmentsJpaController bookCont = new BookedAppointmentsJpaController(entityMF);
        try {
            selectedAppointment.setStatus("booked");
            controller.edit(selectedAppointment);

            BookedAppointmentsJpaController connn = new BookedAppointmentsJpaController(entityMF);

            BookedAppointments newBooking = new BookedAppointments(appointmentId, userId, "waiting");

            connn.create(newBooking);

            AppoitmentsTable.getItems().remove(selectedAppointment);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Appointment booked successfully!");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to book the appointment!");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select an appointment to book!");
        alert.showAndWait();
    }
}

    @FXML
    private void logoutAction(ActionEvent event) {
        ViewManager.cloesPatientDashBoardStage();
    }

    @FXML
    private void logOutAction(MouseEvent event) {
        ViewManager.cloesPatientDashBoardStage();
    }

}
