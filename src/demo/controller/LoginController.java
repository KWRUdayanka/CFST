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

    @FXML
    public void onLogin() {
        Login login = new Login();
        login.setCquEmail(emailFiled.getText());
        login.setPassword(passwordField.getText());
        Pair<Boolean, User> loggedIn = new UserLoginService().onUserLogin(login);
        if (!loggedIn.getKey()) {
            userLoggingLabel.setText("Login Failed! Check Your Email and Password.");
        } else{
            FXMLLoader fxmlLoader = null;
            Parent root = null;

            if (Objects.equals(loggedIn.getValue().getType(), "Admin")){
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/adminScreen.fxml"));
            } else if ((Objects.equals(loggedIn.getValue().getType(), "Student") || Objects.equals(loggedIn.getValue().getType(), "Staff"))) {
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/homeScreen.fxml"));
            } else if (Objects.equals(loggedIn.getValue().getType(), "Manager")) {
                fxmlLoader = new FXMLLoader(Main.class.getResource("view/managerScreen.fxml"));
            }

            try {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                assert fxmlLoader != null;
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage registerStage = new Stage();
                registerStage.setTitle("Register!");
                registerStage.setScene(scene);
                registerStage.show();
                HomeScreenController homeScreenController = fxmlLoader.getController();
                homeScreenController.setUserInformation(loggedIn.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    public void onRegister() {
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
