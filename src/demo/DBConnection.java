package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private Connection databaseLink;

    public Connection getConnection(){

        if (databaseLink == null) {
            String databaseName = "computingCF";
            String databaseUser = "sanuri";
            String databasePassword = "123";
            String url = "jdbc:mysql://localhost/" + databaseName;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return databaseLink;
    }
}
