package demo.controller;

import demo.Main;
import demo.persistance.Login;
import demo.persistance.User;
import demo.service.UserLoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

    // Injecting UI elements from FXML file using @FXML annotation
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField emailFiled;
    @FXML
    private TextField passwordField;
    @FXML
    private Label userLoggingLabel;

    // This method is called when the login button is clicked
    @FXML
    public void onLogin() {

        // Check if both the email and password fields are not empty
        if (!emailFiled.getText().isBlank() && !passwordField.getText().isBlank()) {
            loginValidation(); // If both fields are not empty, call loginValidation() method
        } else {
            // Otherwise, display an error message
            userLoggingLabel.setText("Please Enter Username and password.");
        }
    }

    // This method checks if the entered login details are valid or not
    private void loginValidation() {
        Login login = new Login();
        login.setCquEmail(emailFiled.getText());
        login.setPassword(passwordField.getText());
        // Call the user login service to validate the user's login details
        Pair<Boolean, User> loggedIn = new UserLoginService().onUserLogin(login);

        // If the login was not successful, display an error message
        if (!loggedIn.getKey()) {
            userLoggingLabel.setText("Login Failed! Check Your Email and Password.");
        } else{
            FXMLLoader fxmlLoader = null;
            Parent root = null;

            if (Objects.equals(loggedIn.getValue().getType(), "Admin")){
                // If the user is an admin, load the admin screen
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/adminScreen.fxml"));
            } else if ((Objects.equals(loggedIn.getValue().getType(), "Student") || Objects.equals(loggedIn.getValue().getType(), "Staff"))) {
                // If the user is a student or staff member, load the home screen
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/homeScreen.fxml"));
            } else if (Objects.equals(loggedIn.getValue().getType(), "Manager")) {
                // If the user is a manager, load the manager screen
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/managerScreen.fxml"));
            }

            try {
                // Close the current login window
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                // Load the appropriate screen based on the user's role
                assert fxmlLoader != null;
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage registerStage = new Stage();
                registerStage.setTitle("Register!");
                registerStage.setScene(scene);
                registerStage.show();
                // Pass the user's information to the appropriate controller
                HomeScreenController homeScreenController = fxmlLoader.getController();
                homeScreenController.setUserInformation(loggedIn.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    // This method is called when the register button is clicked
    @FXML
    public void onRegister() {
        // Close the current window
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("Register!");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
