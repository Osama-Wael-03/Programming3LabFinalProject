/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author user
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class DoctorLoginScreenStage extends Stage{

    public DoctorLoginScreenStage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewFXML/doctorloginScreen.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    this.setTitle("Doctor Login Page");
    this.setScene(scene); 
    }
    
    
    
}
