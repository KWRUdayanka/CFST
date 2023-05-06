package demo.service;

import demo.DBConnection;
import demo.persistance.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegistrationService {
    public UserRegistrationService() {
    }

    public void userRegistration(User user)  {
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = conn.getConnection();

            statement = dbConnection.createStatement();
            statement.executeUpdate(createUserTable);
            statement.close();

            preparedStatement = dbConnection.prepareStatement(addUserDetailSQL);
            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setString(2,user.getCquEmail());
            preparedStatement.setString(3,encryptPassword(user.getPassword()));
            preparedStatement.setString(4,user.getType());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getPhoneNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String encryptPassword(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static final String addUserDetailSQL = "INSERT INTO TBL_USER(FULL_NAME,CQU_EMAIL,PASSWORD,TYPE,ADDRESS,PHONE_NUMBER)" +
            " VALUES(?,?,?,?,?,?)";

    private static final String createUserTable =   "CREATE TABLE IF NOT EXISTS TBL_USER " +
            "(USER_ID int NOT NULL AUTO_INCREMENT, " +
            " FULL_NAME VARCHAR(255) NOT NULL, " +
            " CQU_EMAIL VARCHAR(255) NOT NULL, " +
            " PASSWORD VARCHAR(255) NOT NULL, " +
            " TYPE VARCHAR(15) NOT NULL, " +
            " ADDRESS VARCHAR(225), " +
            " PHONE_NUMBER VARCHAR(225), " +
            " PRIMARY KEY ( USER_ID ))";
}
