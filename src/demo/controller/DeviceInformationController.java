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

    @FXML
    private Button homeButton;
    @FXML
    private Label saveDeviceSuccessMessage;
    @FXML
    private Label saveDeviceErrorMessage;
    @FXML
    public ChoiceBox<String> deviceTypeChoiceBox;
    private final String[] deviceTypes = {"Desktop + Screen", "Laptop + Screen", "Desktop + 2 screens", "Laptop + screen at office + screen at home" + "Desktop + screen + laptop"};
    @FXML
    private ChoiceBox<String> ownershipChoiceBox;
    private final String[] ownership = {"Owned","University"};
    @FXML
    private ChoiceBox<String> usageChoiceBox;
    private final String [] usage = {"Work", "Study"};
    @FXML
    private TextField manufacturedDateFiled;
    @FXML
    private ChoiceBox<String> deviceModeChoiceBox;
    private final String [] deviceMode = {"Active", "With default power saving features", "Shutdown when not in use", "Turned off at wall when not in use"};

    @FXML
    private ChoiceBox<String> locationChoiceBox;
    private final String[] location = {"Home", "Campus"};
    @FXML
    public static User user;

    @FXML
    private void onSaveDevice() {
        if (deviceTypeChoiceBox.getValue().equals("Device Type") || ownershipChoiceBox.getValue().equals("Owner Ship")
                || usageChoiceBox.getValue().equals("Usage of Device") || locationChoiceBox.getValue().equals("Location")
                || deviceModeChoiceBox.getValue().equals("Device Mode")) {
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

            new AddDeviceService().addDeviceToDatabase(device);

            saveDeviceSuccessMessage.setText("Save Device Successfully!");
        }
    }

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
