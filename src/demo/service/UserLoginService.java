package demo.service;

import demo.DBConnection;
import demo.persistance.Login;
import demo.persistance.User;
import javafx.util.Pair;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserLoginService {
    public Pair<Boolean,User> onUserLogin(Login login) {
        DBConnection conn = new DBConnection();
        Connection dbConnection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int users = 0;
        User user = new User();
        try {
            dbConnection = conn.getConnection();
            statement = dbConnection.prepareStatement(getUserByEmailAndPassword);
            statement.setString(1, login.getCquEmail());
            statement.setString(2, encryptPassword(login.getPassword()));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users = resultSet.getInt("TOTAL");
                user.setUsrID(resultSet.getInt("USER_ID"));
                user.setFullName(resultSet.getString("FULL_NAME"));
                user.setType(resultSet.getString("TYPE"));
            }
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (users > 0) {
            return new Pair<>(true, user);
        } else {
            return new Pair<>(false, user);
        }
    }

    public String encryptPassword(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private final static String getUserByEmailAndPassword = "SELECT COUNT(USER_ID) AS TOTAL, FULL_NAME AS FULL_NAME, USER_ID AS USER_ID, TYPE AS TYPE FROM TBL_USER WHERE CQU_EMAIL = ? AND PASSWORD = ?";
}
