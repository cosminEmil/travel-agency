package org.example.newprojectmpp.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.newprojectmpp.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository extends BaseRepository<Employee> {
    public EmployeeRepository(Connection connection) {
        super(connection, "employees");
    }

    @Override
    protected String getInsertFields() {
        return "(name, email, password)";
    }

    @Override
    protected String getPlaceholders() {
        return "(?, ?, ?)";
    }

    @Override
    protected String getSearchCondition() {
        return "email = ? AND password = ?";
    }

    @Override
    protected String getDeleteCondition() {
        return "email = ?";
    }

    @Override
    protected void setAddParameters(PreparedStatement stmt, Employee employee) throws SQLException {
        stmt.setString(1, employee.getName());
        stmt.setString(2, employee.getEmail());
        stmt.setString(3, employee.getPassword());
    }

    @Override
    protected void setSearchParameters(PreparedStatement stmt, Employee entity) throws SQLException {
        stmt.setString(1, entity.getEmail());
        stmt.setString(2, entity.getPassword());
    }

    @Override
    protected void setRemoveParameters(PreparedStatement stmt, Employee entity) throws SQLException {
        stmt.setString(1, entity.getEmail());
    }

    @Override
    protected void setId(Employee entity, int id) {
        entity.setId(id);
    }

    public ObservableList<Employee> findAll() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employees";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch employees", e);
            throw new RuntimeException("Database error", e);
        }
        return employees;
    }

}
