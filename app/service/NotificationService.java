package com.example.app.service;

import com.example.app.dao.TaskDAO;
import com.example.app.entity.Task;

public class NotificationService {
    private TaskDAO taskDAO = new TaskDAO();

    public void notifyTaskUpdate(int taskId) {
        Task task = taskDAO.getById(taskId);
        if (task != null && task.getAssignedEmployee() != null) {
            System.out.println("Notification envoyée à " + task.getAssignedEmployee().getName() +
                    " pour la tâche : " + task.getName());
        } else {
            System.out.println("Impossible d'envoyer la notification : tâche ou employé non trouvé.");
        }
    }
}
