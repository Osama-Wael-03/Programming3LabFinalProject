package View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class PatientloginscreenStage extends Stage{

    public PatientloginscreenStage() throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewFXML/patientloginscreen.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    this.setTitle("patient loginscreen");
    this.setScene(scene);     
    }
    
    
    
    
    
    
}
