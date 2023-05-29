/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.UsersJpaController;
import Controller.UsersJpaController;
import Model.Users;
import View.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class NewPatientRegistrationController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private RadioButton male;
    @FXML
    private ToggleGroup gendertg;
    @FXML
    private RadioButton female;
    @FXML
    private Button registrButton;
    @FXML
    private Button cancelButton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registrAction(ActionEvent event) {

        try{
        RadioButton selectedRadioButton = (RadioButton) gendertg.getSelectedToggle();
        String selectedData = selectedRadioButton.getText();

        Users user = new Users(this.username.getText(), this.password.getText(),
                this.firstname.getText(), this.lastname.getText(), Integer.parseInt(this.age.getText()),
                this.email.getText(), this.phone.getText(), selectedData, "patient");
        UsersJpaController cont = new UsersJpaController(entityMF);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setContentText("Patient Added Successfully");
            alert.showAndWait();
        cont.create(user);
        this.resetData();
        
        }catch(Exception ex){
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error on Adding Patient");
            alert.setContentText("Please Fill the Field/s You Want To Update");
            alert.showAndWait();
        }

    }

    @FXML
    private void cancelAction(ActionEvent event) {
        ViewManager.closeNewPatientRegistration();
    }
    
    private void resetData(){
    this.username.clear();
    this.password.clear();
    this.email.clear();
    this.firstname.clear();
    this.lastname.clear();
    this.age.clear();
    this.phone.clear();
   
    }

}
