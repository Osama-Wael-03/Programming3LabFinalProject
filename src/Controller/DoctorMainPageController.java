/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DoctorMainPageController implements Initializable {

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
    private TableView<?> PatientTable;
    @FXML
    private TableColumn<?, ?> patientId;
    @FXML
    private TableColumn<?, ?> patientusername;
    @FXML
    private TableColumn<?, ?> patientpassword;
    @FXML
    private TableColumn<?, ?> patientfirstname;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private TextField IDTextfiled;
    @FXML
    private TextField usernameTextfiled;
    @FXML
    private TextField firstnameTextfiled;
    @FXML
    private TextField lastNameTextfiled;
    @FXML
    private Button logoutbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void patientPageButton(ActionEvent event) {
    }

    @FXML
    private void FreeAppoitmentsButton(ActionEvent event) {
    }

    @FXML
    private void BokkedAppoitmentsButton(ActionEvent event) {
    }

    @FXML
    private void CreateAction(ActionEvent event) {
    }

    @FXML
    private void ShowAction(ActionEvent event) {
    }

    @FXML
    private void SeacrchAction(ActionEvent event) {
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
    }

    @FXML
    private void UpdateAction(ActionEvent event) {
    }

    @FXML
    private void logoutAction(ActionEvent event) {
    }
    
}
