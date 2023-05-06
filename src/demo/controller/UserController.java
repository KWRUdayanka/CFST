package demo.controller;

import demo.persistance.User;
import demo.service.UserRegistrationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Button homeButton;
    @FXML
    private TextField fullNameFiled;
    @FXML
    private TextField cquEmailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox<String> userRoleChoiceBox;
    private final String[] userRoles = {"Student", "Staff"};

    public void registerButtonOnAction(ActionEvent e) {
        User user = new User();
        user.setFullName(fullNameFiled.getText());
        user.setCquEmail(cquEmailField.getText());
        user.setPassword(passwordField.getText());
        user.setType(userRoleChoiceBox.getValue());
        user.setAddress(addressField.getText());
        user.setPhoneNumber(phoneNumberField.getText());
        new UserRegistrationService().userRegistration(user);

        registrationMessageLabel.setText("User has been registered successfully!");
    }

    public void goToHomeButton(ActionEvent e) {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRoleChoiceBox.getItems().addAll(userRoles);
        userRoleChoiceBox.setValue("Student");
    }
}