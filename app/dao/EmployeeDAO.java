package com.example.app.dao;

import com.example.app.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static List<Employee> employees = new ArrayList<>();
    private static int currentId = 1;

    public void save(Employee employee) {
        employee.setId(currentId++);
        employees.add(employee);
        System.out.println("Employé sauvegardé : " + employee.getName());
    }

    public Employee getById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void update(Employee employee) {
        // Mise à jour fictive
        System.out.println("Employé mis à jour : " + employee.getName());
    }

    public void delete(Employee employee) {
        employees.remove(employee);
        System.out.println("Employé supprimé : " + employee.getName());
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}
