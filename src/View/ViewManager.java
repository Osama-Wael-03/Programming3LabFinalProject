package View;

/**
 *
 * @author user
 */
import Controller.UpdateUserDataController;
import Model.Users;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

    public static MainScreenStage main;
    public static DoctorLoginScreenStage doctorLoginScreenStage;
    public static NewPatientRegistrationStage newPatientRegistrationStage;
    public static PatientloginscreenStage patientloginscreenStage;
    public static DoctorDashBordStage doctorDashBordStage;
    public static PatientDashBordStage patientDashBordStage;
    public static DoctorFreeAppointmentsStage doctorFreeAppointmentsStage;
    public static UpdatePatientDataStage updatePatientDataStage;

    public ViewManager() {
    }

    public static void openMainPage() throws IOException {
        if (main == null) {
            main = new MainScreenStage();
            main.show();
        } else {
            main.show();
        }
    }

    public static void closeMainPage() {
        if (main != null) {
            main.close();
        }
    }

    public static void openDoctorLoginScreen() throws IOException {
        if (doctorLoginScreenStage == null) {
            doctorLoginScreenStage = new DoctorLoginScreenStage();
            doctorLoginScreenStage.show();
        } else {
            doctorLoginScreenStage.show();
        }
    }

    public static void closeDoctorLoginScreen() {
        if (doctorLoginScreenStage != null) {
            doctorLoginScreenStage.close();
        }
    }

    public static void openNewPatientRegistration() throws IOException {
        if (newPatientRegistrationStage == null) {
            newPatientRegistrationStage = new NewPatientRegistrationStage();
            newPatientRegistrationStage.show();
        } else {
            newPatientRegistrationStage.show();
        }
    }

    public static void closeNewPatientRegistration() {
        if (newPatientRegistrationStage != null) {
            newPatientRegistrationStage.close();
        }
    }

    public static void openPatientloginscreenStage() throws IOException {
        if (patientloginscreenStage == null) {
            patientloginscreenStage = new PatientloginscreenStage();
            patientloginscreenStage.show();
        } else {
            patientloginscreenStage.show();
        }
    }

    public static void closePatientloginscreenStage() {
        if (patientloginscreenStage != null) {
            patientloginscreenStage.close();
        }
    }

    public static void openDoctorDashBordStage() throws IOException {
        if (doctorDashBordStage == null) {
            doctorDashBordStage = new DoctorDashBordStage();
            doctorDashBordStage.show();
        } else {
            doctorDashBordStage.show();
        }
    }

    public static void closeDoctorFashBordStage() {
        if (doctorDashBordStage != null) {
            doctorDashBordStage.close();
        }
    }

    public static void openPatientDashBoardStage() throws IOException, Exception {
        if (patientDashBordStage == null) {
            patientDashBordStage = new PatientDashBordStage();
            patientDashBordStage.show();
        } else {
            patientDashBordStage.show();
        }
    }

    public static void cloesPatientDashBoardStage() {
        if (patientDashBordStage != null) {
            patientDashBordStage.close();
        }
    }

    public static void openDoctorFreeAppointmentsStage() throws IOException {
        if (doctorFreeAppointmentsStage == null) {
            doctorFreeAppointmentsStage = new DoctorFreeAppointmentsStage();
            doctorFreeAppointmentsStage.show();
        } else {
            doctorFreeAppointmentsStage.show();
        }
    }

    public static void closeDoctorFreeAppointmentsStage() {
        if (doctorFreeAppointmentsStage != null) {
            doctorFreeAppointmentsStage.close();
        }
    }

    public static void openUpdatePatientDataStage(Users selectedUser) throws IOException {
    FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource("/viewFXML/UpdateUserData.fxml"));
    Parent root = loader.load();
    UpdateUserDataController controller = loader.getController();
    controller.setUserData(selectedUser);
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
}

    public static void closeUpdatePtientDataStage() {
        if (updatePatientDataStage != null) {
            updatePatientDataStage.close();
        }
    }
}
