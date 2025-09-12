package com.example.correctionapp.entity;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Objects;

public class Project {
    private int id;
    private String name;
    private String description;
    private List<Employee> employees;
    private List<Task> tasks;

    public Project() {
        this.employees = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public Project(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    // Logique métier enrichie
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("L'employé ne peut pas être null");
        }
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employee == null) return;

        // Vérifier si l'employé a des tâches assignées
        boolean hasAssignedTasks = tasks.stream()
                .anyMatch(task -> employee.equals(task.getAssignedEmployee()));

        if (hasAssignedTasks) {
            throw new IllegalStateException("Impossible de retirer l'employé : il a des tâches assignées");
        }

        employees.remove(employee);
    }

    public Task addTask(String taskName, String description) {
        Task task = new Task(taskName, description);
        task.setProject(this);
        tasks.add(task);
        return task;
    }

    public void removeTask(Task task) {
        if (task != null && tasks.contains(task)) {
            // Désassigner l'employé si nécessaire
            if (task.getAssignedEmployee() != null) {
                task.getAssignedEmployee().unassignTask(task);
            }
            task.setProject(null);
            tasks.remove(task);
        }
    }

    public boolean hasEmployee(Employee employee) {
        return employees.contains(employee);
    }

    public double getCompletionPercentage() {
        if (tasks.isEmpty()) return 0.0;

        long completedTasks = tasks.stream()
                .filter(Task::isCompleted)
                .count();

        return (double) completedTasks / tasks.size() * 100.0;
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    // Validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du projet ne peut pas être vide");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : null;
    }

    // Getters (collections immuables)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees != null ? new ArrayList<>(employees) : new ArrayList<>();
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks != null ? new ArrayList<>(tasks) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Project{id=%d, name='%s', employees=%d, tasks=%d, completion=%.1f%%}",
                id, name, employees.size(), tasks.size(), getCompletionPercentage());
    }
}