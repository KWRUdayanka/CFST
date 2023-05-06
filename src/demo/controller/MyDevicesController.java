package demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyDevicesController {

    @FXML
    private Button homeButton;
    @FXML
    private void goToHome() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }
}
