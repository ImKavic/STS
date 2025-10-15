package com.sts;

import com.sts.db.DatabaseConnection;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection();
    }
}
