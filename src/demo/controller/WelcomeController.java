package demo.controller;

import demo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private ImageView welcomeImageView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File image = new File("images/R.jpg");
        Image welcomeImage = new Image(image.toURI().toString());
        welcomeImageView.setImage(welcomeImage);
    }
}