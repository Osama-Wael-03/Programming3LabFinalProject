package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class PatientDashBordStage extends Stage{
    private Scene PatientDashbordFreeAppoitments;
    
    private Scene PatientDashbordBokkedappointments;

    public PatientDashBordStage() throws  Exception{
    FXMLLoader FreeAppoitmentsScene = new FXMLLoader(getClass().getResource("/viewFXML/PatientDashbordFreeAppoitments.fxml"));
    Parent freeApRoot = FreeAppoitmentsScene.load();
    PatientDashbordFreeAppoitments = new Scene(freeApRoot);    
    
    FXMLLoader BokkedAppoitmentsScene = new FXMLLoader(getClass().getResource("/viewFXML/PatientDashbordBokkedappointments.fxml"));
    Parent BokkedApRoot = BokkedAppoitmentsScene.load();
    PatientDashbordBokkedappointments = new Scene(BokkedApRoot);  
    
    this.setTitle("patient Page");
    this.setScene(PatientDashbordFreeAppoitments);
    
    }
    
    public void changeSceneToPatientDashbordBokkedappointments (){
    this.setScene(PatientDashbordBokkedappointments);
    }
    public void changeSceneToPatientDashbordFreeappointments (){
    this.setScene(PatientDashbordFreeAppoitments);
    }
    
    
}
