package org.example;

import org.example.newprojectmpp.model.Employee;
import org.example.newprojectmpp.repository.EmployeeRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    // Initialize logger
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        String url = "jdbc:sqlite:identifier.sqlite";

        logger.info("Starting application");
        logger.debug("Attempting to connect to database at: {}", url);

        try (Connection connection = DriverManager.getConnection(url)) {
            logger.info("Database connection established successfully!");

            // Initialize repositories
            EmployeeRepository employeeRepo = new EmployeeRepository(connection);

            logger.debug("Repository initialized");

            // Create and add employee

            Employee juniorEngineer = new Employee("Bucur Cosmin Emil", "cosmin@test.com", "fsegaWorstPassword1234");
            employeeRepo.add(juniorEngineer);
            logger.info("Added employee: {}", juniorEngineer.getName());

        } catch (SQLException e) {
            logger.error("Database connection failed: {}", e.getMessage(), e);
        }
        logger.info("Application shutdown");
    }
}