package com.example.correctionapp.service;

import com.example.correctionapp.dao.EmployeeDAO;
import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.exception.EmployeeNotFoundException;

import java.util.List;

import java.util.Optional;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(String name, String role, String email) {
        Employee employee = new Employee(name, role, email);
        employeeDAO.save(employee);
        return employee;
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeDAO.findById(id);
    }

    public void removeEmployee(int id) {
        Employee employee = employeeDAO.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employé avec ID " + id + " non trouvé"));

        if (!employee.getAssignedTasks().isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer l'employé : il a des tâches assignées");
        }

        employeeDAO.delete(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public List<Employee> findEmployeesByRole(String role) {
        return employeeDAO.findByCriteria(emp -> emp.hasRole(role));
    }

    public List<Employee> getAvailableEmployees() {
        return employeeDAO.findByCriteria(Employee::canTakeMoreTasks);
    }
}