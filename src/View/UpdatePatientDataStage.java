/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author msi1
 */
public class UpdatePatientDataStage extends Stage{
    
    

    public UpdatePatientDataStage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewFXML/UpdateUserData.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    this.setTitle("Update Patient Data page");
    this.setScene(scene); 
    }
    
}

