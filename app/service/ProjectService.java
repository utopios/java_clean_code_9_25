package com.example.app.service;

import com.example.app.dao.EmployeeDAO;
import com.example.app.dao.ProjectDAO;
import com.example.app.entity.Employee;
import com.example.app.entity.Project;
import com.example.app.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private ProjectDAO projectDAO = new ProjectDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void createProject(String name) {
        Project project = new Project();
        project.setName(name);
        project.setEmployees(new ArrayList<Employee>());
        project.setTasks(new ArrayList<Task>());
        projectDAO.save(project);
    }

    public void assignEmployeeToProject(int projectId, int employeeId) {
        Project project = projectDAO.getById(projectId);
        Employee employee = employeeDAO.getById(employeeId);
        if (project != null && employee != null) {
            if (project.getEmployees() == null) {
                project.setEmployees(new ArrayList<Employee>());
            }
            project.getEmployees().add(employee);
            projectDAO.update(project);
        } else {
            System.out.println("Erreur : Projet ou employé non trouvé.");
        }
    }

    public void printProjectDetails(int projectId) {
        Project project = projectDAO.getById(projectId);
        if (project != null) {
            System.out.println("Projet : " + project.getName());
            System.out.println("Employés assignés :");
            if (project.getEmployees() != null) {
                for (Employee e : project.getEmployees()) {
                    System.out.println("- " + e.getName() + " (" + e.getRole() + ")");
                }
            } else {
                System.out.println("Aucun employé assigné.");
            }
            System.out.println("Tâches associées :");
            if (project.getTasks() != null) {
                for (Task t : project.getTasks()) {
                    System.out.println("- " + t.getName() + " [Statut: " + t.getStatus() + "]");
                    if (t.getAssignedEmployee() != null) {
                        System.out.println("  Assignée à : " + t.getAssignedEmployee().getName());
                    } else {
                        System.out.println("  Aucun employé assigné.");
                    }
                }
            } else {
                System.out.println("Aucune tâche associée.");
            }
        } else {
            System.out.println("Projet non trouvé.");
        }
    }

    public void getAllProjects() {
        List<Project> projects = projectDAO.getAllProjects();
        System.out.println("Liste des projets :");
        for (Project p : projects) {
            System.out.println("- " + p.getName());
        }
    }
}