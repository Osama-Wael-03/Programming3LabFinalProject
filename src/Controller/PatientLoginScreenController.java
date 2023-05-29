/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Users;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.sessions.Session;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PatientLoginScreenController implements Initializable {

    @FXML
    private TextField userNameTextfiled;
    @FXML
    private PasswordField passwordTextfiled;
    @FXML
    private Button loginButton;
    @FXML
    private Button signuPButton;

    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        String username = userNameTextfiled.getText();
        String password = passwordTextfiled.getText();

        UsersJpaController usersController = new UsersJpaController(entityMF);

        List<Users> usersList = usersController.findUsersByUsernameAndPassword(username, password);

        if (!usersList.isEmpty()) {
            Users user = usersList.get(0);
            int userId = user.getId();

            UserController.setLoggedInUserId(userId);

            ViewManager.openPatientDashBoardStage();
            ViewManager.closePatientloginscreenStage();
            ViewManager.closeMainPage();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please enter valid UserName and Password.");
            alert.showAndWait();
        }
    }

    @FXML
    private void signupAction(ActionEvent event) throws IOException {
        ViewManager.openNewPatientRegistration();
    }

}
