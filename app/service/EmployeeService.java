package com.example.app.service;

import com.example.app.dao.EmployeeDAO;
import com.example.app.entity.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void addEmployee(String name, String role) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employeeDAO.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getById(id);
    }

    public void removeEmployee(int id) {
        Employee employee = employeeDAO.getById(id);
        if (employee != null) {
            employeeDAO.delete(employee);
        } else {
            System.out.println("Employé non trouvé.");
        }
    }

    public void getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("Liste des employés :");
        for (Employee e : employees) {
            System.out.println("- " + e.getName() + " (" + e.getRole() + ")");
        }
    }
}
