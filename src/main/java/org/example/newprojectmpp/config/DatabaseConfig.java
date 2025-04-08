package org.example.newprojectmpp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite"; // File-based DB
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to SQLite", e);
            }
        }
        return connection;
    }

}