package Controller;

import Model.Appointments;
import Model.BookedAppointments;
import View.ViewManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PatientDashbordBokkedappointmentsController implements Initializable {

    @FXML
    private Button FreeAppoitmentsPage;
    @FXML
    private Button BokkedAppoitmentsPage;
    @FXML
    private Pane LogOut;
    @FXML
    private Button ShowButton;
    @FXML
    private TableView<BookedAppointments> AppoitmentsTable;
    @FXML
    private TableColumn<BookedAppointments, Integer> idcolumn;
    @FXML
    private TableColumn<BookedAppointments, String> datecolumn;
    @FXML
    private TableColumn<BookedAppointments, String> daycolumn;
    @FXML
    private TableColumn<BookedAppointments, String> timecolumn;
    @FXML
    private TableColumn<BookedAppointments, String> statusColumn;
    @FXML
    private TextArea NotesTextArea;
    @FXML
    private Button logoutbutton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppoitmentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                BookedAppointments selectedAppointment = AppoitmentsTable.getSelectionModel().getSelectedItem();
                String doctorComment = selectedAppointment.getDoctorComment();
                if (doctorComment != null && !doctorComment.isEmpty()) {
                    NotesTextArea.setText(doctorComment);
                } else {
                    NotesTextArea.setText("No comment available");
                }
            } else {
                NotesTextArea.clear();
            }
        });
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
    private void ShowAction(ActionEvent event) {
        fetchBookedAppointments();
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        ViewManager.cloesPatientDashBoardStage();

    }

    @FXML
    private void logOutAction(MouseEvent event) {
        ViewManager.cloesPatientDashBoardStage();

    }

    private void fetchBookedAppointments() {
        EntityManager entityManager = entityMF.createEntityManager();
        entityManager.getTransaction().begin();

        int userId = UserController.getLoggedInUserId();
        String queryStr = "SELECT b FROM BookedAppointments b WHERE b.userId.id = :userId AND b.status IN ('waiting', 'finished')";
        TypedQuery<BookedAppointments> query = entityManager.createQuery(queryStr, BookedAppointments.class);
        query.setParameter("userId", userId);
        List<BookedAppointments> bookedAppointmentsList = query.getResultList();

        ObservableList<BookedAppointments> appointmentsData = FXCollections.observableArrayList(bookedAppointmentsList);

        idcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        datecolumn.setCellValueFactory(cellData -> {
            Appointments appointment = cellData.getValue().getAppointmentId();
            String appointmentDate = appointment != null ? appointment.getAppointmentDate() : "";
            return new SimpleStringProperty(appointmentDate);
        });
        daycolumn.setCellValueFactory(cellData -> {
            Appointments appointment = cellData.getValue().getAppointmentId();
            String appointmentDay = appointment != null ? appointment.getAppointmentDay() : "";
            return new SimpleStringProperty(appointmentDay);
        });
        timecolumn.setCellValueFactory(cellData -> {
            Appointments appointment = cellData.getValue().getAppointmentId();
            String appointmentTime = appointment != null ? appointment.getAppointmentTime() : "";
            return new SimpleStringProperty(appointmentTime);
        });
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        AppoitmentsTable.setItems(appointmentsData);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
