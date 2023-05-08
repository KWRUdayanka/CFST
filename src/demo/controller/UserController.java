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


    // Injecting UI elements from FXML file using @FXML annotation
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label registrationErrorMessageLabel;
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
    // Array of user roles to populate choice box
    private final String[] userRoles = {"Student", "Staff", "Manager"};


    // Action to perform when user clicks the register button
    public void registerButtonOnAction(ActionEvent e) {

        // Checks if all the fields are filled with valid values
        if (!fullNameFiled.getText().isBlank() || !cquEmailField.getText().isBlank() || !passwordField.getText().isBlank() || !addressField.getText().isBlank() || !phoneNumberField.getText().isBlank()) {
            // Creates a new User object with the values entered by the user
            User user = new User();
            user.setFullName(fullNameFiled.getText());
            user.setCquEmail(cquEmailField.getText());
            user.setPassword(passwordField.getText());
            user.setType(userRoleChoiceBox.getValue());
            user.setAddress(addressField.getText());
            user.setPhoneNumber(phoneNumberField.getText());

            // Calls the user registration service to register the user
            new UserRegistrationService().userRegistration(user);

            // Displays a success message to the user
            registrationMessageLabel.setText("User has been registered successfully!");
        } else {
            // Displays a success message to the user
            registrationErrorMessageLabel.setText("Every fields must be filed!");
        }
    }

    // Handles the action when the Home button is clicked
    public void goToHomeButton(ActionEvent e) {
        // Closes the current window
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializes the user role choice box with the available options
        userRoleChoiceBox.getItems().addAll(userRoles);
        // Initializes the user role choice box with the available options
        userRoleChoiceBox.setValue("Student");
    }
}