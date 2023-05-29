package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class DoctorDashBordStage extends Stage{

    private Scene DoctorDashBorsPatientScene ;
    
    private Scene DoctorDashBordFreeAppointmensScene;
          
    private Scene DoctorDashBordBokkedAppointmensScene;     
    
    
    public DoctorDashBordStage() throws IOException{
    FXMLLoader patientpagescene = new FXMLLoader(getClass().getResource("/viewFXML/DoctorDashBorsPatientPage.fxml"));
    Parent patientpageRoot = patientpagescene.load();
    DoctorDashBorsPatientScene = new Scene(patientpageRoot);
    
    
    FXMLLoader freeAppoitmentsScene = new FXMLLoader(getClass().getResource("/viewFXML/DoctorDashBordFreeAppointmens.fxml"));
    Parent freeAppoitmentsRoot = freeAppoitmentsScene.load();
    DoctorDashBordFreeAppointmensScene = new Scene(freeAppoitmentsRoot);
    
    
    FXMLLoader bokkedAppoitmentsScene = new FXMLLoader(getClass().getResource("/viewFXML/DoctorDashBordBokkedAppointmens.fxml"));
    Parent bokkedAppoitmentsRoot = bokkedAppoitmentsScene.load();
    DoctorDashBordBokkedAppointmensScene = new Scene(bokkedAppoitmentsRoot);
     
    
    this.setTitle("Doctor DashBoard");
    this.setScene(DoctorDashBorsPatientScene);
   
    
    }
    
    public void changesceneTopatientPage(){
    this.setScene(DoctorDashBorsPatientScene);
    }
    public void changesceneToFreeAppointmens(){
        
    this.setScene(DoctorDashBordFreeAppointmensScene);
    }
    
    public void changesceneToBordBokkedAppoint(){
    
    this.setScene(DoctorDashBordBokkedAppointmensScene);
    }
    
    
    
}
