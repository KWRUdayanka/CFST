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
            dbConnection = conn.getConnection();

            statement = dbConnection.createStatement();
            statement.executeUpdate(createDeviceTable);
            statement.close();

            preparedStatement = dbConnection.prepareStatement(addDeviceDetailSQL);
            preparedStatement.setString(1,device.getDeviceType());
            preparedStatement.setString(2,device.getOwnership());
            preparedStatement.setString(3,device.getUsageOfDevice());
            preparedStatement.setString(4,device.getManufacturedDate());
            preparedStatement.setString(5,device.getPurchasedDate());
            preparedStatement.setString(6,device.getLocation());
            preparedStatement.setInt(7,device.getUserId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String addDeviceDetailSQL = "INSERT INTO TBL_DEVICES(DEVICE_TYPE,OWNERSHIP,USAGE,MANUFACTURED_DATE,PURCHASED_DATE,LOCATION,USER_ID)" +
            " VALUES(?,?,?,?,?,?,?)";

    private static final String createDeviceTable =   "CREATE TABLE IF NOT EXISTS TBL_DEVICES " +
            "(ID int NOT NULL AUTO_INCREMENT, " +
            " DEVICE_TYPE VARCHAR(255) NOT NULL, " +
            " OWNERSHIP VARCHAR(255) NOT NULL, " +
            " USAGE VARCHAR(255), " +
            " MANUFACTURED_DATE VARCHAR(15), " +
            " PURCHASED_DATE VARCHAR(225), " +
            " LOCATION VARCHAR(225), " +
            " USER_ID int NOT NULL, " +
            " PRIMARY KEY ( ID )," +
            " FOREIGN KEY (USER_ID) REFERENCES TBL_USER(USER_ID))";
}
