package com.example.correctionapp;

import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.exception.BusinessException;
import com.example.correctionapp.exception.EmployeeNotFoundException;
import com.example.correctionapp.exception.InvalidTaskStatusTransitionException;
import com.example.correctionapp.exception.TaskNotFoundException;
import com.example.correctionapp.service.EmployeeService;
import com.example.correctionapp.service.ProjectService;
import com.example.correctionapp.service.ServiceFactory;
import com.example.correctionapp.service.TaskService;

import java.util.List;

public class Main {
    private final ServiceFactory serviceFactory;

    public Main() {
        this.serviceFactory = ServiceFactory.getInstance();
    }

    public static void main(String[] args) {
        Main app = new Main();

        try {
            app.runApplication();
        } catch (Exception e) {
            System.err.println("Erreur dans l'application : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void runApplication() {
        System.out.println("=== APPLICATION DE GESTION DE PROJETS ===\n");

        // Services
        ProjectService projectService = serviceFactory.getProjectService();
        EmployeeService employeeService = serviceFactory.getEmployeeService();
        TaskService taskService = serviceFactory.getTaskService();

        try {
            System.out.println("1. Création d'un projet...");
            Project project = projectService.createProject("Migration Système", "Migration vers la nouvelle architecture");
            System.out.println("✓ Projet créé : " + project);

            System.out.println("\n2. Ajout d'employés...");
            Employee dev1 = employeeService.addEmployee("John Doe", "Développeur", "john.doe@company.com");
            Employee analyst = employeeService.addEmployee("Jane Smith", "Analyste", "jane.smith@company.com");
            System.out.println("✓ Employé ajouté : " + dev1);
            System.out.println("✓ Employé ajouté : " + analyst);

            System.out.println("\n3. Assignation des employés au projet...");
            projectService.assignEmployeeToProject(project.getId(), dev1.getId());
            projectService.assignEmployeeToProject(project.getId(), analyst.getId());
            System.out.println("✓ Employés assignés au projet");

            System.out.println("\n4. Création de tâches...");
            Task task1 = taskService.createTask("Mettre à jour la base de données",
                    "Migrer les schémas vers la nouvelle version", project.getId());
            Task task2 = taskService.createTask("Analyser les besoins",
                    "Identifier les exigences de migration", project.getId());
            System.out.println("✓ Tâche créée : " + task1);
            System.out.println("✓ Tâche créée : " + task2);

            System.out.println("\n5. Assignation d'employés aux tâches...");
            taskService.assignEmployeeToTask(task1.getId(), dev1.getId());
            taskService.assignEmployeeToTask(task2.getId(), analyst.getId());
            System.out.println("✓ Tâches assignées");

            System.out.println("\n6. Mise à jour du statut des tâches...");
            taskService.updateTaskStatus(task1.getId(), TaskStatus.IN_PROGRESS);
            taskService.updateTaskStatus(task2.getId(), TaskStatus.IN_PROGRESS);
            System.out.println("✓ Statuts mis à jour");

            System.out.println("\n7. Terminer une tâche...");
            taskService.updateTaskStatus(task2.getId(), TaskStatus.DONE);
            System.out.println("✓ Tâche d'analyse terminée");

            System.out.println("\n8. Affichage des détails du projet...");
            ProjectService.ProjectDetails details = projectService.getProjectDetails(project.getId());
            details.printDetails();

            System.out.println("\n9. Tests de gestion d'erreurs...");
            demonstrateErrorHandling(taskService, employeeService);

            System.out.println("\n10. Affichage des statistiques...");
            displayStatistics(taskService, employeeService, projectService);

        } catch (BusinessException e) {
            System.err.printf("Erreur métier : %s\n", e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== FIN DE L'APPLICATION ===");
    }

    private void demonstrateErrorHandling(TaskService taskService, EmployeeService employeeService) {
        System.out.println("\n--- Tests de gestion d'erreurs ---");

        try {
            taskService.updateTaskStatus(999, TaskStatus.DONE);
        } catch (TaskNotFoundException e) {
            System.out.println("✓ Erreur capturée: " + e.getMessage());
        }

        try {
            taskService.assignEmployeeToTask(1, 999);
        } catch (EmployeeNotFoundException e) {
            System.out.println("✓ Erreur capturée: " + e.getMessage());
        }

        try {
            Task task = taskService.createTask("Test Task", 1);
            taskService.updateTaskStatus(task.getId(), TaskStatus.DONE);
        } catch (InvalidTaskStatusTransitionException e) {
            System.out.println("✓ Erreur capturée: " + e.getMessage());
        }

        try {
            employeeService.addEmployee("", "Developer", "test@test.com");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Erreur de validation capturée: " + e.getMessage());
        }
    }

    private void displayStatistics(TaskService taskService, EmployeeService employeeService, ProjectService projectService) {
        System.out.println("\n--- STATISTIQUES ---");

        List<Employee> employees = employeeService.getAllEmployees();
        System.out.printf("Nombre total d'employés : %d\n", employees.size());

        List<Employee> availableEmployees = employeeService.getAvailableEmployees();
        System.out.printf("Employés disponibles : %d\n", availableEmployees.size());

        System.out.println("\nRépartition des tâches par statut :");
        for (TaskStatus status : TaskStatus.values()) {
            List<Task> tasksByStatus = taskService.findTasksByStatus(status);
            System.out.printf("  %s : %d tâche(s)\n", status.getDisplayName(), tasksByStatus.size());
        }

        List<Project> projects = projectService.findAllProjects();
        System.out.printf("\nNombre total de projets : %d\n", projects.size());

        if (!projects.isEmpty()) {
            double avgCompletion = projects.stream()
                    .mapToDouble(Project::getCompletionPercentage)
                    .average()
                    .orElse(0.0);
            System.out.printf("Progression moyenne : %.1f%%\n", avgCompletion);
        }
    }
}