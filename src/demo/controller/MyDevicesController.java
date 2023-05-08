package demo.controller;

import demo.persistance.Device;
import demo.persistance.DeviceList;
import demo.persistance.User;
import demo.service.DeviceDetailsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class MyDevicesController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private TableView<DeviceList> deviceTable;
    @FXML
    private TableColumn<Device,String> deviceType;
    @FXML
    private TableColumn<Device,String> ownership;
    @FXML
    private TableColumn<Device,String> usage;
    @FXML
    private TableColumn<Device,String> location;
    @FXML
    private static User user; // Current user

    // Method to set user information
    public void setUserInformation(User staticUser) {
        user = staticUser;
        List<Device> devices = new DeviceDetailsService().getDeviceDetails(user.getUsrID());
        if (!devices.isEmpty()) {
            // Create an observable list of DeviceList objects to populate the table
            ObservableList<DeviceList> list = FXCollections.observableArrayList();
            devices.forEach(x -> list.addAll(new DeviceList(x.getDeviceType(), x.getOwnership(), x.getUsageOfDevice(), x.getLocation())));
            deviceTable.setItems(list);
        }
    }

    // Method to handle going back to the home screen
    @FXML
    private void goToHome() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    // Method to initialize the table columns
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deviceType.setCellValueFactory(new PropertyValueFactory<Device,String>("Device Type"));
        ownership.setCellValueFactory(new PropertyValueFactory<Device,String>("Ownership"));
        usage.setCellValueFactory(new PropertyValueFactory<Device,String>("Usage"));
        location.setCellValueFactory(new PropertyValueFactory<Device,String>("Location"));
    }
}
