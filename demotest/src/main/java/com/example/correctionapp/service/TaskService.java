package com.example.correctionapp.service;


import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(String name, int projectId);
    Task createTask(String name, String description, int projectId);
    void updateTaskStatus(int taskId, TaskStatus status);
    void assignEmployeeToTask(int taskId, int employeeId);
    Optional<Task> findTaskById(int taskId);
    List<Task> findAllTasks();
    List<Task> findTasksByProject(int projectId);
    List<Task> findTasksByStatus(TaskStatus status);
    void deleteTask(int taskId);
}