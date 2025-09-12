package com.example.correctionapp.service;

import com.example.correctionapp.dao.EmployeeDAO;
import com.example.correctionapp.dao.ProjectDAO;
import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.exception.EmployeeNotFoundException;
import com.example.correctionapp.exception.ProjectNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProjectService {
    private final ProjectDAO projectDAO;
    private final EmployeeDAO employeeDAO;

    public ProjectService(ProjectDAO projectDAO, EmployeeDAO employeeDAO) {
        this.projectDAO = projectDAO;
        this.employeeDAO = employeeDAO;
    }

    public Project createProject(String name, String description) {
        Project project = new Project(name, description);
        projectDAO.save(project);
        return project;
    }

    public void assignEmployeeToProject(int projectId, int employeeId) {
        Project project = projectDAO.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Projet avec ID " + projectId + " non trouvé"));

        Employee employee = employeeDAO.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employé avec ID " + employeeId + " non trouvé"));

        project.addEmployee(employee);
        projectDAO.update(project);
    }

    public void removeEmployeeFromProject(int projectId, int employeeId) {
        Project project = projectDAO.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Projet avec ID " + projectId + " non trouvé"));

        Employee employee = employeeDAO.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employé avec ID " + employeeId + " non trouvé"));

        project.removeEmployee(employee);
        projectDAO.update(project);
    }

    public Optional<Project> findProjectById(int projectId) {
        return projectDAO.findById(projectId);
    }

    public List<Project> findAllProjects() {
        return projectDAO.findAll();
    }

    public ProjectDetails getProjectDetails(int projectId) {
        Project project = projectDAO.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Projet avec ID " + projectId + " non trouvé"));

        return new ProjectDetails(project);
    }
    public static class ProjectDetails {
        private final Project project;

        public ProjectDetails(Project project) {
            this.project = project;
        }

        public void printDetails() {
            System.out.println("=== DÉTAILS DU PROJET ===");
            System.out.println("Projet : " + project.getName());
            if (project.getDescription() != null) {
                System.out.println("Description : " + project.getDescription());
            }
            System.out.printf("Progression : %.1f%%\n", project.getCompletionPercentage());

            System.out.println("\nEmployés assignés :");
            if (project.getEmployees().isEmpty()) {
                System.out.println("  Aucun employé assigné.");
            } else {
                project.getEmployees().forEach(employee ->
                        System.out.printf("  - %s (%s) - %d tâches actives\n",
                                employee.getName(), employee.getRole(), employee.getActiveTasksCount()));
            }

            System.out.println("\nTâches associées :");
            if (project.getTasks().isEmpty()) {
                System.out.println("  Aucune tâche associée.");
            } else {
                project.getTasks().forEach(task -> {
                    System.out.printf("  - %s [%s]\n", task.getName(), task.getStatus().getDisplayName());
                    if (task.getAssignedEmployee() != null) {
                        System.out.println("    Assignée à : " + task.getAssignedEmployee().getName());
                    } else {
                        System.out.println("    Aucun employé assigné.");
                    }
                });
            }
            System.out.println("========================");
        }

        public Project getProject() {
            return project;
        }
    }
}