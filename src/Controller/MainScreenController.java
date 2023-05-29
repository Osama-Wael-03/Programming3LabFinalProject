/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button DoctorButton;
    @FXML
    private Button PatientButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DoctorButtonAction(ActionEvent event) throws IOException {
        ViewManager.openDoctorLoginScreen();
    }

    @FXML
    private void PatientButtonAction(ActionEvent event) throws IOException {
        ViewManager.openPatientloginscreenStage();
    }
    
}
