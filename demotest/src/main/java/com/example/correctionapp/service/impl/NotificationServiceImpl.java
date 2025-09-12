package com.example.correctionapp.service.impl;

import com.example.correctionapp.dao.TaskDAO;
import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    private final TaskDAO taskDAO;

    public NotificationServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public void notifyTaskStatusChanged(Task task, TaskStatus oldStatus, TaskStatus newStatus) {
        if (task.getAssignedEmployee() != null) {
            String message = String.format(
                    "Bonjour %s,\n\nLa tâche '%s' est passée de '%s' à '%s'.\n\nCordialement,\nL'équipe projet",
                    task.getAssignedEmployee().getName(),
                    task.getName(),
                    oldStatus.getDisplayName(),
                    newStatus.getDisplayName()
            );

            sendNotification(task.getAssignedEmployee(), "Mise à jour de tâche", message);
        }
    }

    @Override
    public void notifyTaskAssigned(Task task, Employee previousEmployee, Employee newEmployee) {
        if (newEmployee != null) {
            String message = String.format(
                    "Bonjour %s,\n\nVous avez été assigné(e) à la tâche '%s' du projet '%s'.\n\nCordialement,\nL'équipe projet",
                    newEmployee.getName(),
                    task.getName(),
                    task.getProject().getName()
            );

            sendNotification(newEmployee, "Nouvelle tâche assignée", message);
        }

        if (previousEmployee != null && !previousEmployee.equals(newEmployee)) {
            String message = String.format(
                    "Bonjour %s,\n\nLa tâche '%s' ne vous est plus assignée.\n\nCordialement,\nL'équipe projet",
                    previousEmployee.getName(),
                    task.getName()
            );

            sendNotification(previousEmployee, "Tâche désassignée", message);
        }
    }

    @Override
    public void notifyTaskUpdate(int taskId) {
        Task task = taskDAO.findById(taskId).orElse(null);
        if (task != null && task.getAssignedEmployee() != null) {
            String message = String.format(
                    "Notification envoyée à %s pour la tâche : %s",
                    task.getAssignedEmployee().getName(),
                    task.getName()
            );
            System.out.println(message);
        } else {
            System.out.println("Impossible d'envoyer la notification : tâche ou employé non trouvé.");
        }
    }

    private void sendNotification(Employee employee, String subject, String message) {
        System.out.println("=== NOTIFICATION ===");
        System.out.println("À: " + employee.getName() + " (" + employee.getEmail() + ")");
        System.out.println("Sujet: " + subject);
        System.out.println("Message:");
        System.out.println(message);
        System.out.println("==================");
    }
}
