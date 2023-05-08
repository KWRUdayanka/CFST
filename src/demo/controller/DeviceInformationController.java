package demo.controller;

import demo.persistance.Device;
import demo.persistance.User;
import demo.service.AddDeviceService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeviceInformationController implements Initializable{

    // These are the JavaFX elements defined in the corresponding FXML file
    @FXML
    private Button homeButton;
    @FXML
    private Label saveDeviceSuccessMessage;
    @FXML
    private Label saveDeviceErrorMessage;
    @FXML
    public ChoiceBox<String> deviceTypeChoiceBox;
    // These are the options that will be displayed in the deviceTypeChoiceBox
    private final String[] deviceTypes = {"Desktop + Screen", "Laptop + Screen", "Desktop + 2 screens", "Laptop + screen at office + screen at home" + "Desktop + screen + laptop"};
    @FXML
    private ChoiceBox<String> ownershipChoiceBox;
    // These are the options that will be displayed in the ownershipChoiceBox
    private final String[] ownership = {"Owned","University"};
    @FXML
    private ChoiceBox<String> usageChoiceBox;
    // These are the options that will be displayed in the usageChoiceBox
    private final String [] usage = {"Work", "Study"};
    @FXML
    private TextField manufacturedDateFiled;
    @FXML
    private ChoiceBox<String> deviceModeChoiceBox;
    // These are the options that will be displayed in the deviceModeChoiceBox
    private final String [] deviceMode = {"Active", "With default power saving features", "Shutdown when not in use", "Turned off at wall when not in use"};

    @FXML
    private ChoiceBox<String> locationChoiceBox;
    // These are the options that will be displayed in the locationChoiceBox
    private final String[] location = {"Home", "Campus"};
    @FXML
    public static User user;

    // This method is called when the user clicks the Save Device button
    @FXML
    private void onSaveDevice() {
        // Check if any of the required fields is empty
        if (deviceTypeChoiceBox.getValue().equals("Device Type") || ownershipChoiceBox.getValue().equals("Owner Ship")
                || usageChoiceBox.getValue().equals("Usage of Device") || locationChoiceBox.getValue().equals("Location")
                || deviceModeChoiceBox.getValue().equals("Device Mode")) {
            // If any of the required fields is empty, display an error message
            saveDeviceErrorMessage.setText("Dropbox data should Not be empty!");
        } else {
            Device device = new Device();
            device.setDeviceType(deviceTypeChoiceBox.getValue());
            device.setOwnership(ownershipChoiceBox.getValue());
            device.setUsageOfDevice(usageChoiceBox.getValue());
            device.setManufacturedDate(manufacturedDateFiled.getText());
            device.setDeviceMode(deviceModeChoiceBox.getValue());
            device.setLocation(locationChoiceBox.getValue());
            device.setUserId(user.getUsrID());

            // Add the new device to the database using the AddDeviceService
            new AddDeviceService().addDeviceToDatabase(device);
            // Display a success message
            saveDeviceSuccessMessage.setText("Save Device Successfully!");
        }
    }

    // This method is called when the user clicks the Home button
    @FXML
    private void goToHome() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deviceTypeChoiceBox.getItems().addAll(deviceTypes);
        deviceTypeChoiceBox.setValue("Device Type");
        ownershipChoiceBox.getItems().addAll(ownership);
        ownershipChoiceBox.setValue("Owner Ship");
        usageChoiceBox.getItems().addAll(usage);
        usageChoiceBox.setValue("Usage of Device");
        locationChoiceBox.getItems().addAll(location);
        locationChoiceBox.setValue("Location");
        deviceModeChoiceBox.getItems().addAll(deviceMode);
        deviceModeChoiceBox.setValue("Device Mode");
    }

    public void setUserInformation(User staticUser) {
        user = staticUser;
    }
}
