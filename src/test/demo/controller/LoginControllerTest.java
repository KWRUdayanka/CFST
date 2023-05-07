package demo.controller;

import demo.Main;
import demo.persistance.Login;
import demo.persistance.User;
import demo.service.UserLoginService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void onLogin() {
        loginValidation();
    }

    private void loginValidation() {
        Login login = new Login();
        login.setCquEmail("b.blob@@cqu.edu.au");
        login.setPassword("1234");
        Pair<Boolean, User> loggedIn = new UserLoginService().onUserLogin(login);
        if (!loggedIn.getKey()) {
            System.out.println("Login Failed! Check Your Email and Password.");
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
}