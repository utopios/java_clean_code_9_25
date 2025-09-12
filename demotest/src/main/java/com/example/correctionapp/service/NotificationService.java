package com.example.correctionapp.service;


import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;

public interface NotificationService {
    void notifyTaskStatusChanged(Task task, TaskStatus oldStatus, TaskStatus newStatus);
    void notifyTaskAssigned(Task task, Employee previousEmployee, Employee newEmployee);
    void notifyTaskUpdate(int taskId);
}
