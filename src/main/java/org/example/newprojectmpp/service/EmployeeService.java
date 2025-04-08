package org.example.newprojectmpp.service;

import org.example.newprojectmpp.model.Employee;
import org.example.newprojectmpp.repository.IRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    private IRepository<Employee> employeeRepository;

    public EmployeeService(IRepository<Employee> employeeRepository) {
        this.employeeRepository = employeeRepository;
        logger.info("EmployeeService initialized");
    }

    public EmployeeService() {

    }

    public void addEmployee(String name, String email, String password) {
        logger.debug("Adding new employee: {}", email);
        Employee employee = new Employee(name, email, password);
        employeeRepository.add(employee);
        logger.info("Employee added successfully: {}", email);
    }

    public boolean employeeExists(String email, String password) {
        logger.debug("Checking if employee exists: {}", email);
        Employee searchEmployee = new Employee(null, email, password);
        return employeeRepository.search(searchEmployee);
    }

    public void removeEmployee(String email) {
        logger.debug("Removing employee: {}", email);
        Employee employee = new Employee(null, email, null);
        employeeRepository.remove(employee);
        logger.info("Employee removed: {}", email);
    }
}