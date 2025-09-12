package com.example.app.entity;

public class Task {
    private int id;
    private String name;
    private String status;
    private Project project;
    private Employee assignedEmployee;

    public Task() {
    }

    public Task(int id, String name, String status, Project project, Employee assignedEmployee) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.project = project;
        this.assignedEmployee = assignedEmployee;
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
}
