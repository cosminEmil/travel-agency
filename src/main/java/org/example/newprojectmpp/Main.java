package org.example.newprojectmpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.newprojectmpp.config.DatabaseConfig;
import org.example.newprojectmpp.controller.EmployeeController;
import org.example.newprojectmpp.repository.EmployeeRepository;
import org.example.newprojectmpp.service.EmployeeService;
import org.example.newprojectmpp.view.EmployeeView;

import java.sql.Connection;
import java.sql.SQLException;

import static org.example.newprojectmpp.config.DatabaseConfig.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Connection connection = DatabaseConfig.getConnection();
        EmployeeRepository repository = new EmployeeRepository(connection);
        EmployeeService service = new EmployeeService(repository);

        // Initialize and show the view
        EmployeeView view = new EmployeeView(primaryStage);
        view.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}