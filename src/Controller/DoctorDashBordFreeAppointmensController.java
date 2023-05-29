/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointments;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DoctorDashBordFreeAppointmensController implements Initializable {

    @FXML
    private Button patientPage;
    @FXML
    private Button FreeAppoitmentsPage;
    @FXML
    private Button BokkedAppoitmentsPage;
    @FXML
    private Button CreateAppoitments;
    @FXML
    private Button ShowButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button SearchButton;
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
    private Button DeleteButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private TextField DateTextfiled;
    @FXML
    private TextField DayTextfiled;
    @FXML
    private TextField TimeTextfiled;
    @FXML
    private Button logoitbutton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.idcolumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.datecolumn.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        this.daycolumn.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        this.timecolumn.setCellValueFactory(new PropertyValueFactory("appointmentTime"));

        AppoitmentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                DateTextfiled.setText(newSelection.getAppointmentDate());
                DayTextfiled.setText(newSelection.getAppointmentDay());
                TimeTextfiled.setText(newSelection.getAppointmentTime());
            } else {

                DateTextfiled.clear();
                DayTextfiled.clear();
                TimeTextfiled.clear();
            }
        });
    }

    @FXML
    private void patientPageButton(ActionEvent event) {
        ViewManager.doctorDashBordStage.changesceneTopatientPage();

    }

    @FXML
    private void FreeAppoitmentsButton(ActionEvent event) {
        ViewManager.doctorDashBordStage.changesceneToFreeAppointmens();
    }

    @FXML
    private void BokkedAppoitmentsButton(ActionEvent event) {
            ViewManager.doctorDashBordStage.changesceneToBordBokkedAppoint();
    }

    @FXML
    private void CreateAction(ActionEvent event) {

        String date = this.DateTextfiled.getText();
        String time = this.TimeTextfiled.getText();
        String day = this.DayTextfiled.getText();
        if (!(date.equals("") && time.equals("") && day.equals(""))) {
            String status = "free";
            AppointmentsJpaController cont = new AppointmentsJpaController(entityMF);
            Appointments newAppointment = new Appointments(date, day, time, status);
            cont.create(newAppointment);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setContentText("Appointment Added Successfully!");
            alert.showAndWait();
            this.DateTextfiled.clear();
            this.TimeTextfiled.clear();
            this.DayTextfiled.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setContentText("Failed To Add Appointment!");
            alert.showAndWait();
        }
    }

    @FXML
    private void ShowAction(ActionEvent event) {
        List<Appointments> freeAppointments = new AppointmentsJpaController(entityMF).findFreeAppointmentsEntities();
        AppoitmentsTable.getItems().clear();
        AppoitmentsTable.getItems().addAll(freeAppointments);
    }

    @FXML
    private void SeacrchAction(ActionEvent event) {
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
        Appointments selectedAppointment = AppoitmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null) {
            AppointmentsJpaController controller = new AppointmentsJpaController(entityMF);
            try {
                controller.destroy(selectedAppointment.getId());
                AppoitmentsTable.getItems().remove(selectedAppointment);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Appointment deleted successfully!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete the appointment!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an appointment to delete!");
            alert.showAndWait();
        }
    }

    @FXML
    private void UpdateAction(ActionEvent event) {
        Appointments selectedAppointment = AppoitmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null) {
            String updatedDate = DateTextfiled.getText();
            String updatedTime = TimeTextfiled.getText();
            String updatedDay = DayTextfiled.getText();

            if (!updatedDate.isEmpty() && !updatedTime.isEmpty() && !updatedDay.isEmpty()) {
                selectedAppointment.setAppointmentDate(updatedDate);
                selectedAppointment.setAppointmentTime(updatedTime);
                selectedAppointment.setAppointmentDay(updatedDay);

                AppointmentsJpaController controller = new AppointmentsJpaController(entityMF);
                try {
                    controller.edit(selectedAppointment);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Appointment updated successfully!");
                    alert.showAndWait();
                    DateTextfiled.clear();
                    TimeTextfiled.clear();
                    DayTextfiled.clear();
                    AppoitmentsTable.refresh();
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to update the appointment!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fields!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an appointment to update!");
            alert.showAndWait();
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        ViewManager.closeDoctorFashBordStage();

    }

}
