/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Users;
import View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateUserDataController {

    @FXML
    private TextField UsernnameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private RadioButton female;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private ToggleGroup gendertg;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private RadioButton male;

    @FXML
    private TextField passwordTexeField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button registrButton;
    EntityManagerFactory entityMF = Persistence.createEntityManagerFactory("FinalProject2PU");
    private Users selectedUser;

    public void setUserData(Users user) {
        selectedUser = user;
        populateFields();
    }

   private void populateFields() {
    if (selectedUser != null) {
        UsernnameTextField.setText(selectedUser.getUsername());
        firstnameTextField.setText(selectedUser.getFirstname());
        passwordTexeField.setText(selectedUser.getPassword());
        lastnameTextField.setText(selectedUser.getLastname());
        ageTextField.setText(String.valueOf(selectedUser.getAge()));
        emailTextField.setText(selectedUser.getEmail());
        phoneTextField.setText(selectedUser.getPhone());

        String gender = selectedUser.getGender();
        if (gender.equalsIgnoreCase("male")) {
            male.setSelected(true);
        } else if (gender.equalsIgnoreCase("female")) {
            female.setSelected(true);
        }
    }
}

    @FXML
    void cancelAction(ActionEvent event) {

        ViewManager.closeUpdatePtientDataStage();
    }

    @FXML
    void registrAction(ActionEvent event) {

        if (selectedUser != null) {
        // Get the updated values from the fields
        String username = UsernnameTextField.getText();
        String firstname = firstnameTextField.getText();
        String password = passwordTexeField.getText();
        String lastname = lastnameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String gender = male.isSelected() ? "Male" : "Female"; // Get the selected gender

        // Update the user object with the new values
        selectedUser.setUsername(username);
        selectedUser.setFirstname(firstname);
        selectedUser.setPassword(password);
        selectedUser.setLastname(lastname);
        selectedUser.setAge(age);
        selectedUser.setEmail(email);
        selectedUser.setPhone(phone);
        selectedUser.setGender(gender);

        // Save the updated user in the database
        UsersJpaController cont = new UsersJpaController(entityMF);
        try {
            cont.edit(selectedUser);
            showAlert("Success", "User data updated successfully.");
            ViewManager.closeUpdatePtientDataStage();
            
        } catch (Exception e) {
            showAlert("Error", "Failed to update user data.");
        }
    }
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
