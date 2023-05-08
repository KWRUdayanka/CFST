// This class is responsible for creating a database connection to the MySQL server
package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private Connection databaseLink;

    // This method creates a connection to the MySQL server
    public Connection getConnection(){

        if (databaseLink == null) {
            // Database credentials
            String databaseName = "computingCF";
            String databaseUser = "sanuri";
            String databasePassword = "123";
            String url = "jdbc:mysql://localhost/" + databaseName;

            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Create a connection to the database
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return databaseLink;
    }
}
