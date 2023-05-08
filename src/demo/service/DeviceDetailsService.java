package demo.service;

import demo.DBConnection;
import demo.persistance.Device;
import demo.persistance.GenerateReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeviceDetailsService {

    // This method retrieves device details for a specific user
    public List<Device> getDeviceDetails(int usrID) {
        // Establish connection to the database
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Device> deviceList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            dbConnection = conn.getConnection();
            // Prepare SQL statement with the user ID parameter
            statement = dbConnection.prepareStatement(getDevicesByUserId);
            statement.setInt(1, usrID);
            // Execute the statement and iterate through the result set
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Create a new Device object for each row in the result set
                Device device = new Device();
                device.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                device.setOwnership(resultSet.getString("OWNERSHIP"));
                device.setUsageOfDevice(resultSet.getString("DEVICE_USAGE"));
                device.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                device.setLocation(resultSet.getString("LOCATION"));
                assert false;
                // Add the Device object to the deviceList
                deviceList.add(device);
            }
            // Close the statement and connection
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            // Throw a runtime exception if an SQL exception occurs
            throw new RuntimeException(e);
        }
        // Return the list of Device objects
        return deviceList;
    }

    // This method retrieves details for all devices
    public List<Device> getAllDeviceDetails() {
        // Establish connection to the database
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Device> deviceList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            dbConnection = conn.getConnection();
            // Prepare SQL statement to retrieve all devices
            statement = dbConnection.prepareStatement(getAllDevices);
            // Execute the statement and iterate through the result set
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Create a new Device object for each row in the result set
                Device device = new Device();
                device.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                device.setOwnership(resultSet.getString("OWNERSHIP"));
                device.setUsageOfDevice(resultSet.getString("DEVICE_USAGE"));
                device.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                device.setLocation(resultSet.getString("LOCATION"));
                assert false;
                // Add the Device object to the deviceList
                deviceList.add(device);
            }
            // Close the statement and connection
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            // Throw a runtime exception if an SQL exception occurs
            throw new RuntimeException(e);
        }
        // Return the list of Device objects
        return deviceList;
    }

    // This method retrieves details for all devices and their associated users
    public List<GenerateReport> getAllDeviceANDUserDetails() {
        // Establish connection to the database
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<GenerateReport> deviceListReport = new ArrayList<GenerateReport>();
        ResultSet resultSet = null;
        try {
            dbConnection = conn.getConnection();
            // Prepare SQL statement to retrieve all devices and their associated users
            statement = dbConnection.prepareStatement(getAllDevicesAndUse);
            // Execute the statement and iterate through the result set
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Create a new GenerateReport object and set its properties based on the data in the result set
                GenerateReport generateReport = new GenerateReport();
                generateReport.setFullName(resultSet.getString("FULL_NAME"));
                generateReport.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                generateReport.setCquEmail(resultSet.getString("CQU_EMAIL"));
                generateReport.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                generateReport.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                assert false;
                // Add the GenerateReport object to the list
                deviceListReport.add(generateReport);
            }
            // Close the statement and database connection
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            // Throw a RuntimeException if there is a SQL exception
            throw new RuntimeException(e);
        }
        // Return the list of GenerateReport objects
        return deviceListReport;
    }

    private final static String getDevicesByUserId = "SELECT DEVICE_TYPE AS DEVICE_TYPE, OWNERSHIP AS OWNERSHIP, DEVICE_USAGE AS DEVICE_USAGE, DEVICE_MODE AS DEVICE_MODE, LOCATION AS LOCATION  FROM TBL_DEVICES WHERE USER_ID = ?";

    private final static String getAllDevices = "SELECT DEVICE_TYPE AS DEVICE_TYPE, OWNERSHIP AS OWNERSHIP, DEVICE_USAGE AS DEVICE_USAGE, DEVICE_MODE AS DEVICE_MODE, LOCATION AS LOCATION  FROM TBL_DEVICES";

    private final static String getAllDevicesAndUse = "SELECT FULL_NAME AS FULL_NAME, CQU_EMAIL AS CQU_EMAIL, DEVICE_TYPE AS DEVICE_TYPE, DEVICE_MODE AS DEVICE_MODE,PHONE_NUMBER AS PHONE_NUMBER FROM TBL_USER INNER JOIN TBL_DEVICES ON TBL_USER.USER_ID = TBL_DEVICES.USER_ID";
}
