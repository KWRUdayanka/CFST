package demo.controller;

import demo.Main;
import demo.persistance.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreenController {

    @FXML
    private Button logOutButton;
    @FXML
    public static User staticUser;

    @FXML
    private void onViewMyProfile() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/myProfile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("My Profile!!");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void onViewMyDevice() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/myDevices.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("My Devices!!");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void onAddDeviceInformation() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/deviceInformation.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("Add Device Information!!");
            registerStage.setScene(scene);
            registerStage.show();
            DeviceInformationController deviceInformationController = fxmlLoader.getController();
            deviceInformationController.setUserInformation(staticUser);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void onLogOut() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void setUserInformation(User user) {
        staticUser = user;
    }
}
