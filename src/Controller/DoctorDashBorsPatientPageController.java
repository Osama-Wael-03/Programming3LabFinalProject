/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Model.Users;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DoctorDashBorsPatientPageController implements Initializable {

    @FXML
    private Button patientPage;
    @FXML
    private Button FreeAppoitmentsPage;
    @FXML
    private Button BokkedAppoitmentsPage;
    @FXML
    private Button CreatePatient;
    @FXML
    private Button ShowButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button SearchButton;
    @FXML
    private TableView<Users> PatientTable;
    @FXML
    private TableColumn<Users, Integer> patientId;
    @FXML
    private TableColumn<Users, String> patientusername;
    @FXML
    private TableColumn<Users, String> patientfirstname;
    @FXML
    private TableColumn<Users, String> patientLastName;
    @FXML
    private TableColumn<Users, Integer> ageTableColumn;

    @FXML
    private TableColumn<Users, String> emailTableColumn;

    @FXML
    private TableColumn<Users, String> genderTableColumn;
    @FXML
    private TableColumn<Users, String> passwordTableColumn;
    @FXML
    private TableColumn<Users, String> phoneTableColumn;

    @FXML
    private Button DeleteButton;
    @FXML
    private Button UpdateButton;

    @FXML
    private Button logoutbutton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.patientId.setCellValueFactory(new PropertyValueFactory("id"));
        this.patientusername.setCellValueFactory(new PropertyValueFactory("username"));
        this.passwordTableColumn.setCellValueFactory(new PropertyValueFactory("password"));
        this.patientfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        this.patientLastName.setCellValueFactory(new PropertyValueFactory("lastname"));
        this.ageTableColumn.setCellValueFactory(new PropertyValueFactory("age"));
        this.emailTableColumn.setCellValueFactory(new PropertyValueFactory("email"));
        this.phoneTableColumn.setCellValueFactory(new PropertyValueFactory("phone"));
        this.genderTableColumn.setCellValueFactory(new PropertyValueFactory("gender"));

    }

    @FXML
    private void patientPageButton(ActionEvent event) {
        ViewManager.doctorDashBordStage.changesceneTopatientPage();
    }

    @FXML
    private void FreeAppoitmentsButton(ActionEvent event) throws IOException {

        ViewManager.doctorDashBordStage.changesceneToFreeAppointmens();

    }

    @FXML
    private void BokkedAppoitmentsButton(ActionEvent event) throws IOException {

        ViewManager.doctorDashBordStage.changesceneToBordBokkedAppoint();

    }

    @FXML
    private void CreateAction(ActionEvent event) throws IOException {

        ViewManager.openNewPatientRegistration();
    }

    @FXML
    private void ShowAction(ActionEvent event) {

        UsersJpaController cont = new UsersJpaController(entityMF);
        this.PatientTable.getItems().setAll(cont.findPatients());

    }

    @FXML
    private void SeacrchAction(ActionEvent event) {
        UsersJpaController cont = new UsersJpaController(entityMF);

        this.PatientTable.getItems().setAll(cont.findUsersByFirstName(this.searchBar.getText()));
    }

    @FXML
    private void DeleteAction(ActionEvent event) throws NonexistentEntityException {
        Users selectedUser = PatientTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UsersJpaController cont = new UsersJpaController(entityMF);
            cont.destroy(selectedUser.getId());
            PatientTable.getItems().remove(selectedUser);
        }
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws IOException {
        Users selectedUser = PatientTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
           
            showAlert("No Row Selected", "Please choose a row from the table.");
        } else {
            ViewManager.openUpdatePatientDataStage(selectedUser);
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {

        ViewManager.closeDoctorFashBordStage();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
