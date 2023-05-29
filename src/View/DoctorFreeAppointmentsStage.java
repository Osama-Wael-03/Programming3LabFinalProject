package View;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HP
 */
public class DoctorFreeAppointmentsStage extends Stage {

    public DoctorFreeAppointmentsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewFXML/DoctorDashBordFreeAppointmens.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.setTitle("Doctor Free Appointments Page");
        this.setScene(scene);
    }
}
