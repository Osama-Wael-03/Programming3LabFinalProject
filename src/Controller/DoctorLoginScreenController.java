/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Users;
import View.ViewManager;
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

/**
 * FXML Controller class
 *
 * @author user
 */
public class DoctorLoginScreenController implements Initializable {

    @FXML
    private TextField userNameTextfiled;
    @FXML
    private PasswordField passwordTextfiled;
    @FXML
    private Button loginButton;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
private void loginButtonAction(ActionEvent event) throws Exception {
    String username = userNameTextfiled.getText();
    String password = passwordTextfiled.getText();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject2PU");
    UsersJpaController usersController = new UsersJpaController(emf);

    List<Users> users = usersController.findUsersByUsernameAndPassword(username, password);

    if (users.size() > 0 && users.get(0).getRole().equals("admin")) {
        ViewManager.openDoctorDashBordStage();
        ViewManager.closeDoctorLoginScreen();
        ViewManager.closeMainPage();
    } else {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username, password, or role.");
        alert.showAndWait();
    }
}
}
