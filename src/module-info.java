module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.graphics;


    exports demo;
    opens demo to javafx.fxml;
    exports demo.controller;
    opens demo.controller to javafx.fxml;
}