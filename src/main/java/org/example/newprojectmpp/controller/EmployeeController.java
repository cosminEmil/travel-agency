package org.example.newprojectmpp.controller;

import org.example.newprojectmpp.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        logger.info("EmployeeController initialized");
    }

    public void handleAddEmployee(String name, String email, String password) {
        try {
            logger.debug("Handling add employee request");
            employeeService.addEmployee(name, email, password);
        } catch (Exception e) {
            logger.error("Error adding employee: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean handleLogin(String email, String password) {
        logger.debug("Handling login for: {}", email);
        return employeeService.employeeExists(email, password);
    }

    public void handleRemoveEmployee(String email) {
        logger.debug("Handling employee removal: {}", email);
        employeeService.removeEmployee(email);
    }
}