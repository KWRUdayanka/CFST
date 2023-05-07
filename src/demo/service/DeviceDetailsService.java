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

    DBConnection conn = new DBConnection();
    Connection dbConnection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Device> deviceList = new ArrayList<>();
    Device device = new Device();
    List<GenerateReport> deviceListReport = new ArrayList<GenerateReport>();
    GenerateReport generateReport = new GenerateReport();

    public List<Device> getDeviceDetails(int usrID) {
        try {
            dbConnection = conn.getConnection();
            statement = dbConnection.prepareStatement(getDevicesByUserId);
            statement.setInt(1, usrID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                device.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                device.setOwnership(resultSet.getString("OWNERSHIP"));
                device.setUsageOfDevice(resultSet.getString("DEVICE_USAGE"));
                device.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                device.setLocation(resultSet.getString("LOCATION"));
                assert false;
                deviceList.add(device);
            }
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deviceList;
    }

    public List<Device> getAllDeviceDetails() {
        try {
            dbConnection = conn.getConnection();
            statement = dbConnection.prepareStatement(getAllDevices);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                device.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                device.setOwnership(resultSet.getString("OWNERSHIP"));
                device.setUsageOfDevice(resultSet.getString("DEVICE_USAGE"));
                device.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                device.setLocation(resultSet.getString("LOCATION"));
                assert false;
                deviceList.add(device);
            }
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deviceList;
    }

    public List<GenerateReport> getAllDeviceANDUserDetails() {
        try {
            dbConnection = conn.getConnection();
            statement = dbConnection.prepareStatement(getAllDevicesAndUse);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                generateReport.setFullName(resultSet.getString("FULL_NAME"));
                generateReport.setDeviceType(resultSet.getString("DEVICE_TYPE"));
                generateReport.setCquEmail(resultSet.getString("CQU_EMAIL"));
                generateReport.setDeviceMode(resultSet.getString("DEVICE_MODE"));
                generateReport.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                assert false;
                deviceListReport.add(generateReport);
            }
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deviceListReport;
    }

    private final static String getDevicesByUserId = "SELECT DEVICE_TYPE AS DEVICE_TYPE, OWNERSHIP AS OWNERSHIP, DEVICE_USAGE AS DEVICE_USAGE, DEVICE_MODE AS DEVICE_MODE, LOCATION AS LOCATION  FROM TBL_DEVICES WHERE USER_ID = ?";

    private final static String getAllDevices = "SELECT DEVICE_TYPE AS DEVICE_TYPE, OWNERSHIP AS OWNERSHIP, DEVICE_USAGE AS DEVICE_USAGE, DEVICE_MODE AS DEVICE_MODE, LOCATION AS LOCATION  FROM TBL_DEVICES";

    private final static String getAllDevicesAndUse = "SELECT FULL_NAME AS FULL_NAME, CQU_EMAIL AS CQU_EMAIL, DEVICE_TYPE AS DEVICE_TYPE, DEVICE_MODE AS DEVICE_MODE,PHONE_NUMBER AS PHONE_NUMBER FROM TBL_USER INNER JOIN TBL_DEVICES ON TBL_USER.USER_ID = TBL_DEVICES.USER_ID;";
}
