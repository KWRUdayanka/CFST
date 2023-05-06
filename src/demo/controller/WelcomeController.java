package demo.controller;

import demo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML
    protected void onGoToRegistration() {
        createAccountForm();
    }
    
    @FXML
    protected void onLoginPage() {
        createLoginForm();
    }

    private void createLoginForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("Login!");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm() {
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