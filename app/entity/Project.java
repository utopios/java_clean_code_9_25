package com.example.app.entity;

import java.util.List;

public class Project {
    private int id;
    private String name;
    private List<Employee> employees;
    private List<Task> tasks;

    public Project() {
    }

    public Project(int id, String name, List<Employee> employees, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.tasks = tasks;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}