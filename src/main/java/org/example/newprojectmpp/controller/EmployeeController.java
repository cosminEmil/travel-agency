package org.example.newprojectmpp.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.newprojectmpp.model.Employee;
import org.example.newprojectmpp.service.EmployeeService;
import org.example.newprojectmpp.repository.EmployeeRepository;
import org.example.newprojectmpp.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeController {
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, String> emailColumn;

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private EmployeeService employeeService;
    private ObservableList<Employee> employeeData;

    public EmployeeController() {
        Connection connection = DatabaseConfig.getConnection();
        this.employeeService = new EmployeeService(new EmployeeRepository(connection));
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        loadEmployeeData();
    }

    private void loadEmployeeData() {
        employeeData = (ObservableList<Employee>) employeeService.getAllEmployees();
        employeeTable.setItems(employeeData);
    }

    @FXML
    private void handleAddEmployee() {
        try {
            Employee employee = new Employee(
                    nameField.getText(),
                    emailField.getText(),
                    passwordField.getText()
            );

            employeeService.addEmployee(employee.getName(), employee.getEmail(), employee.getPassword());
            statusLabel.setText("Employee added successfully!");
            loadEmployeeData();
            clearFields();
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeService.removeEmployee(selectedEmployee.getEmail());
            statusLabel.setText("Employee deleted!");
            loadEmployeeData();
        } else {
            statusLabel.setText("Please select an employee to delete");
        }
    }

    @FXML
    private void handleClearFields() {
        clearFields();
        statusLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        boolean isValid = employeeService.employeeExists(
                emailField.getText(),
                passwordField.getText()
        );

        statusLabel.setText(isValid ? "Login successful!" : "Invalid credentials");
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }

}