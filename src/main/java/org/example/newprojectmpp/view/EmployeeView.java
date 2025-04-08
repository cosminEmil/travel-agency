package org.example.newprojectmpp.view;

import org.example.newprojectmpp.controller.EmployeeController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeView {
    private static final Logger logger = LogManager.getLogger(EmployeeView.class);
    private final EmployeeController controller;

    public EmployeeView(EmployeeController controller) {
        this.controller = controller;
    }
    public void show(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        // Create form components
        Label titleLabel = new Label("Employee Management");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setVgap(10);
        formGrid.setHgap(10);

        // Form fields
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Buttons
        Button addButton = new Button("Add Employee");
        Button searchButton = new Button("Search Employee");
        Button deleteButton = new Button("Delete Employee");

        // Add components to grid
        formGrid.add(new Label("Name:"), 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(new Label("Email:"), 0, 1);
        formGrid.add(emailField, 1, 1);
        formGrid.add(new Label("Password:"), 0, 2);
        formGrid.add(passwordField, 1, 2);
        formGrid.add(addButton, 0, 3);
        formGrid.add(searchButton, 1, 3);
        formGrid.add(deleteButton, 2, 3);

        // Result area
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        // Button actions
        addButton.setOnAction(e -> {
            try {
                controller.handleAddEmployee(
                        nameField.getText(),
                        emailField.getText(),
                        passwordField.getText()
                );
                resultArea.setText("Employee added successfully!\n" + emailField.getText());
                logger.info("Employee added through UI: {}", emailField.getText());
            } catch (Exception ex) {
                resultArea.setText("Error: " + ex.getMessage());
                logger.error("Add employee failed through UI", ex);
            }
        });

        searchButton.setOnAction(e -> {
            boolean exists = controller.handleLogin(
                    emailField.getText(),
                    passwordField.getText()
            );
            resultArea.setText(exists ? "Employee exists!" : "Employee not found!");
            logger.debug("Employee search performed for: {}", emailField.getText());
        });

        deleteButton.setOnAction(e -> {
            try {
                controller.handleRemoveEmployee(emailField.getText());
                resultArea.setText("Employee removed!\n" + emailField.getText());
                logger.info("Employee removed through UI: {}", emailField.getText());
            } catch (Exception ex) {
                resultArea.setText("Error: " + ex.getMessage());
                logger.error("Remove employee failed through UI", ex);
            }
        });

        VBox root = new VBox(20, titleLabel, formGrid, resultArea);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
        logger.info("EmployeeView displayed");
    }
}