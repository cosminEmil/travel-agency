package org.example.newprojectmpp.model;

import javafx.beans.property.*;

public class Employee {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public Employee() {}

    public Employee(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    // Property getters
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty emailProperty() { return email; }
    public StringProperty passwordProperty() { return password; }

    // Regular getters and setters
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }

    public String getPassword() { return password.get(); }
    public void setPassword(String password) { this.password.set(password); }
}