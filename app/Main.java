package com.example.app;

import com.example.app.service.EmployeeService;
import com.example.app.service.NotificationService;
import com.example.app.service.ProjectService;
import com.example.app.service.TaskService;

public class Main {
    public static void main(String[] args) {
        ProjectService projectService = new ProjectService();
        EmployeeService employeeService = new EmployeeService();
        TaskService taskService = new TaskService();
        NotificationService notificationService = new NotificationService();

        // Création d'un projet
        projectService.createProject("Migration Système");

        // Ajout d'employés
        employeeService.addEmployee("John Doe", "Développeur");
        employeeService.addEmployee("Jane Smith", "Analyste");

        // Assignation des employés au projet
        projectService.assignEmployeeToProject(1, 1);
        projectService.assignEmployeeToProject(1, 2);

        // Création de tâches
        taskService.createTask("Mettre à jour la base de données", 1);
        taskService.createTask("Analyser les besoins", 1);

        // Assignation d'employés aux tâches
        taskService.assignEmployeeToTask(1, 1);
        taskService.assignEmployeeToTask(2, 2);

        // Mise à jour du statut des tâches
        taskService.updateTaskStatus(1, "En cours");
        taskService.updateTaskStatus(2, "À faire");

        // Envoi de notifications pour les mises à jour des tâches
        notificationService.notifyTaskUpdate(1);
        notificationService.notifyTaskUpdate(2);

        // Affichage des détails du projet
        projectService.printProjectDetails(1);

        // Suppression d'un employé
        employeeService.removeEmployee(2);

        // Mise à jour d'une tâche inexistante (pour tester la gestion des erreurs)
        taskService.updateTaskStatus(99, "Terminée");

        // Tentative d'assignation d'un employé à une tâche inexistante
        taskService.assignEmployeeToTask(99, 1);

        // Récupération de tous les projets
        projectService.getAllProjects();

        // Récupération de tous les employés
        employeeService.getAllEmployees();

        // Récupération de toutes les tâches
        taskService.getAllTasks();
    }
}
