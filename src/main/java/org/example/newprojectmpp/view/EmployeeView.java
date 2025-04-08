package org.example.newprojectmpp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.newprojectmpp.controller.EmployeeController;

import java.io.IOException;

public class EmployeeView {
    private Stage primaryStage;

    public EmployeeView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/newprojectmpp/employee.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            EmployeeController controller = loader.getController();

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Employee Management System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}