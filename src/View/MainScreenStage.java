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
public class MainScreenStage extends Stage{

    public MainScreenStage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewFXML/mainScreen.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    this.setTitle("mainScreen");
    this.setScene(scene);    
    }
    
    
}
