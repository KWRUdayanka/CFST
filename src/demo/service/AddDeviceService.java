/**
*  This class is responsible for adding device details to the database
*  It uses DBConnection class to establish a connection to the database
*  It uses the addDeviceDetailSQL to insert the device details into the database
*  It also creates a device table in the database if it does not exist
*  It throws a RuntimeException if there is an SQL exception
* */
package demo.service;

import demo.DBConnection;
import demo.persistance.Device;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDeviceService {
    public void addDeviceToDatabase(Device device) {
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            // get db connection
            dbConnection = conn.getConnection();

            // create device table if not exists
            statement = dbConnection.createStatement();
            statement.executeUpdate(createDeviceTable);
            statement.close();

            // insert device details into table
            preparedStatement = dbConnection.prepareStatement(addDeviceDetailSQL);
            preparedStatement.setString(1,device.getDeviceType());
            preparedStatement.setString(2,device.getOwnership());
            preparedStatement.setString(3,device.getUsageOfDevice());
            preparedStatement.setString(4,device.getManufacturedDate());
            preparedStatement.setString(5,device.getDeviceMode());
            preparedStatement.setString(6,device.getLocation());
            preparedStatement.setInt(7,device.getUserId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // SQL statement to insert device details into the database
    private static final String addDeviceDetailSQL = "INSERT INTO TBL_DEVICES(DEVICE_TYPE,OWNERSHIP,DEVICE_USAGE,MANUFACTURED_DATE,DEVICE_MODE,LOCATION,USER_ID)" +
            " VALUES(?,?,?,?,?,?,?)";

    // SQL statement to create device table if not exists
    private static final String createDeviceTable =   "CREATE TABLE IF NOT EXISTS TBL_DEVICES " +
            "(ID int NOT NULL AUTO_INCREMENT, " +
            " DEVICE_TYPE VARCHAR(255), " +
            " OWNERSHIP VARCHAR(255), " +
            " DEVICE_USAGE VARCHAR(255), " +
            " MANUFACTURED_DATE VARCHAR(15), " +
            " DEVICE_MODE VARCHAR(225), " +
            " LOCATION VARCHAR(225), " +
            " USER_ID int NOT NULL, " +
            " PRIMARY KEY ( ID )," +
            " FOREIGN KEY (USER_ID) REFERENCES TBL_USER(USER_ID))";
}
