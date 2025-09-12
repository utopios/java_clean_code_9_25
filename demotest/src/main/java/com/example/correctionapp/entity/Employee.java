package com.example.correctionapp.entity;

import com.example.correctionapp.exception.TaskAssignmentException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employee {
    private int id;
    private String name;
    private String role;
    private String email;
    private Set<Task> assignedTasks;
    private static final int MAX_TASKS_PER_EMPLOYEE = 5;

    public Employee() {
        this.assignedTasks = new HashSet<>();
    }

    public Employee(String name, String role, String email) {
        this();
        setName(name);
        setRole(role);
        setEmail(email);
    }

    // Logique métier dans l'entité
    public boolean canTakeMoreTasks() {
        return assignedTasks.size() < MAX_TASKS_PER_EMPLOYEE;
    }

    public void assignTask(Task task) {
        if (!canTakeMoreTasks()) {
            throw new TaskAssignmentException("Employé " + name + " a déjà le maximum de tâches assignées");
        }
        assignedTasks.add(task);
    }

    public void unassignTask(Task task) {
        assignedTasks.remove(task);
    }

    public int getActiveTasksCount() {
        return (int) assignedTasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.DONE)
                .count();
    }

    public boolean hasRole(String roleToCheck) {
        return this.role.equalsIgnoreCase(roleToCheck);
    }

    // Validation dans les setters
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé ne peut pas être vide");
        }
        this.name = name.trim();
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Le rôle ne peut pas être vide");
        }
        this.role = role.trim();
    }

    public void setEmail(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email invalide");
        }
        this.email = email.toLowerCase();
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Getters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public Set<Task> getAssignedTasks() { return new HashSet<>(assignedTasks); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', role='%s', activeTasks=%d}",
                id, name, role, getActiveTasksCount());
    }
}