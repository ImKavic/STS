package com.sts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://Kavic;databaseName=STS;encrypt=false;trustServerCertificate=true;";
        String user = "sa";
        String password = "as";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful to STS!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
