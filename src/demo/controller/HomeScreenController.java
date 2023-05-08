package demo.controller;

import demo.Main;
import demo.persistance.Device;
import demo.persistance.GenerateReport;
import demo.persistance.User;
import demo.service.DeviceDetailsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class HomeScreenController {


    @FXML
    private Label welcomeMessage;
    @FXML
    private Button logOutButton;
    @FXML
    public static User staticUser; // Current user

    // Create a new instance of HomeScreenController class.
    ConcurrentHashMap<String, Double> annualCOTwoe = new ConcurrentHashMap<>();

    public HomeScreenController() {
        // Initialize a ConcurrentHashMap object with the annual CO2e emissions for each device type and mode.
        annualCOTwoe.put("Desktop + Screen",621.0);
        annualCOTwoe.put("Laptop + Screen",691.0);
        annualCOTwoe.put("Desktop + 2 screens",903.0);
        annualCOTwoe.put("Laptop + screen at office + screen at home",928.0);
        annualCOTwoe.put("Desktop + screen + laptop",1030.0);
        annualCOTwoe.put("Active",73.0);
        annualCOTwoe.put("With default power saving features",37.0);
        annualCOTwoe.put("Shutdown when not in use",17.6);
        // These emissions are measured in kg of CO2e emitted annually by each device type and mode.
    }

    // This method is called when the user clicks the onViewMyProfileButton
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

    // This method is called when the user clicks the onViewMyDeviceButton
    @FXML
    private void onViewMyDevice() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/myDevices.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("My Devices!!");
            registerStage.setScene(scene);
            registerStage.show();
            MyDevicesController myDevicesController = fxmlLoader.getController();
            myDevicesController.setUserInformation(staticUser);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // This method is called when the user clicks the onAddDeviceInformationButton
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

    // This method is called when the user clicks the calculateMyCFButton
    @FXML
    private void calculateMyCF() {
        List<Device> devices = new DeviceDetailsService().getDeviceDetails(staticUser.getUsrID());
        calculateTotalCF(devices);
    }

    // This method is called when the user clicks the calculateAllCFButton
    @FXML
    private void calculateAllCF() {
        List<Device> devices = new DeviceDetailsService().getAllDeviceDetails();
        calculateTotalCF(devices);
    }

    /**
     * Calculates the total carbon footprint of all devices
     * and displays an alert with the result.
     * @param devices List of Device objects to calculate carbon footprint for
     */
    private void calculateTotalCF(List<Device> devices) {
        // initialize totalCFKilograms with 0
        AtomicReference<Double> totalCFKilograms = new AtomicReference<>((double) 0);
        // calculate the total carbon footprint for all devices
        devices.forEach(x -> totalCFKilograms.set(totalCFKilograms.get() + (annualCOTwoe.get(x.getDeviceType()) * 0.85 + annualCOTwoe.get(x.getDeviceMode()))));

        // display an alert with the result
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Alert!");
        alert.setHeaderText("Carbon Usage " + totalCFKilograms +"kg");
        alert.showAndWait();
    }

    /**
     * Generates a report of all devices with their carbon usage details
     * and saves it to an excel file.
     */
    @FXML
    private void generateReport() {
        // get all device and user details
        List<GenerateReport> reports = new DeviceDetailsService().getAllDeviceANDUserDetails();

        // create a new excel workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("CarbonUsageDetails");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Full Name");
        header.createCell(1).setCellValue("Device Type");
        header.createCell(2).setCellValue("Carbon Usage");
        header.createCell(3).setCellValue("CQU Email");
        header.createCell(4).setCellValue("Phone Number");

        // add data for each device to the excel sheet
        AtomicInteger index = new AtomicInteger(1);
        reports.forEach(x -> {
            XSSFRow row = sheet.createRow(index.get());
            row.createCell(0).setCellValue(x.getFullName());
            row.createCell(1).setCellValue(x.getDeviceType());
            row.createCell(2).setCellValue(annualCOTwoe.get(x.getDeviceType()) * 0.85 + annualCOTwoe.get(x.getDeviceMode()));
            row.createCell(3).setCellValue(x.getCquEmail());
            row.createCell(4).setCellValue(x.getPhoneNumber());
            index.getAndIncrement();
        });

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("CarbonUsageDetails.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            // display an alert to notify the user that the report has been downloaded
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Alert!");
            alert.setHeaderText("Report has been downloaded.");
            alert.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onLogOut() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void setUserInformation(User user) {
        staticUser = user;
        welcomeMessage.setText("Welcome " + staticUser.getFullName() + "!");
    }

}
