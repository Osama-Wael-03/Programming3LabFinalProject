package Controller;

import Model.Appointments;
import Model.BookedAppointments;
import Model.Users;
import View.ViewManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DoctorDashBordBokkedAppointmensController implements Initializable {

    @FXML
    private Button patientPage;
    @FXML
    private Button FreeAppoitmentsPage;
    @FXML
    private Button BokkedAppoitmentsPage;
    @FXML
    private Pane LogOut;
    @FXML
    private Button ShowButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button SearchButton;
    @FXML
    private TableView<Object> AppoitmentsTable;
    @FXML
    private TableColumn<BookedAppointments, Integer> idColumn;
    @FXML
    private TableColumn<BookedAppointments, String> dateColumn;
    @FXML
    private TableColumn<BookedAppointments, String> dayColumn;
    @FXML
    private TableColumn<BookedAppointments, String> timeColumn;
    @FXML
    private TableColumn<BookedAppointments, String> PatientFnamecolumn;
    @FXML
    private Button finishingButton;
    @FXML
    private TextArea NotesTextArea;
    @FXML
    private Button logOutButton;
    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Link CSS class to TableView
        AppoitmentsTable.getStyleClass().add("../viewFXML/doctor-dashboard-table.css");

        // Link CSS classes to TableColumn elements
        idColumn.getStyleClass().add("column-id");
        dateColumn.getStyleClass().add("column-date");
        dayColumn.getStyleClass().add("column-day");
        timeColumn.getStyleClass().add("column-time");
        PatientFnamecolumn.getStyleClass().add("column-patient-name");
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
        // Do something when the button is clicked
    }

    @FXML
    private void ShowAction(ActionEvent event) {
        fetchBookedAppointments();
    }

    private void fetchBookedAppointments() {
        EntityManager entityManager = entityMF.createEntityManager();
        entityManager.getTransaction().begin();

        List<BookedAppointments> bookedAppointmentsList = entityManager.createQuery("SELECT b FROM BookedAppointments b", BookedAppointments.class)
                .getResultList();

        ObservableList<Object> appointmentsData = FXCollections.observableArrayList(bookedAppointmentsList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentDate = appointment != null ? appointment.getAppointmentDate() : "";
            return new SimpleStringProperty(appointmentDate);
        });
        dayColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentDay = appointment != null ? appointment.getAppointmentDay() : "";
            return new SimpleStringProperty(appointmentDay);
        });
        timeColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentTime = appointment != null ? appointment.getAppointmentTime() : "";
            return new SimpleStringProperty(appointmentTime);
        });
        PatientFnamecolumn.setCellValueFactory(cellData -> {
            Users user = ((BookedAppointments) cellData.getValue()).getUserId();
            String firstName = user != null ? user.getFirstname() : "";
            return new SimpleStringProperty(firstName);
        });

        AppoitmentsTable.setItems(appointmentsData);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @FXML
    private void SeacrchAction(ActionEvent event) {
        String searchQuery = searchBar.getText().trim();
        fetchBookedAppointmentsByFirstName(searchQuery);
    }

    @FXML
    void finishingActionMethod(ActionEvent event) {
        Object selectedAppointment = AppoitmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment instanceof BookedAppointments) {
            BookedAppointments bookedAppointment = (BookedAppointments) selectedAppointment;

            String doctorComment = NotesTextArea.getText();

            bookedAppointment.setDoctorComment(doctorComment);
            bookedAppointment.setStatus("finished");

            EntityManager entityManager = entityMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(bookedAppointment);
            entityManager.getTransaction().commit();
            entityManager.close();

            fetchBookedAppointments();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Finishing operation completed successfully.");
            alert.showAndWait();
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        ViewManager.closeDoctorFashBordStage();
    }

    @FXML
    private void logOutAction(MouseEvent event) {
        ViewManager.closeDoctorFashBordStage();
    }

    private void fetchBookedAppointmentsByFirstName(String firstName) {
        EntityManager entityManager = entityMF.createEntityManager();
        entityManager.getTransaction().begin();

        List<BookedAppointments> bookedAppointmentsList = entityManager.createQuery("SELECT b FROM BookedAppointments b WHERE b.userId.firstname = :firstName", BookedAppointments.class)
                .setParameter("firstName", firstName)
                .getResultList();

        ObservableList<Object> appointmentsData = FXCollections.observableArrayList(bookedAppointmentsList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentDate = appointment != null ? appointment.getAppointmentDate() : "";
            return new SimpleStringProperty(appointmentDate);
        });
        dayColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentDay = appointment != null ? appointment.getAppointmentDay() : "";
            return new SimpleStringProperty(appointmentDay);
        });
        timeColumn.setCellValueFactory(cellData -> {
            Appointments appointment = ((BookedAppointments) cellData.getValue()).getAppointmentId();
            String appointmentTime = appointment != null ? appointment.getAppointmentTime() : "";
            return new SimpleStringProperty(appointmentTime);
        });
        PatientFnamecolumn.setCellValueFactory(cellData -> {
            Users user = ((BookedAppointments) cellData.getValue()).getUserId();
            String firstNameValue = user != null ? user.getFirstname() : "";
            return new SimpleStringProperty(firstNameValue);
        });

        AppoitmentsTable.setItems(appointmentsData);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
