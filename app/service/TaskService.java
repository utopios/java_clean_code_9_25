package com.example.app.service;

import com.example.app.dao.EmployeeDAO;
import com.example.app.dao.ProjectDAO;
import com.example.app.dao.TaskDAO;
import com.example.app.entity.Employee;
import com.example.app.entity.Project;
import com.example.app.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    

    private final GenericDAO<Task,Integer> taskDAO;
    private final GenericDAO<Project,Integer> projectDAO;
    private final GenericDAO<Employee,Integer> employeeDAO;

    public TaskService(GenericDAO<Task,Integer> taskDAO, GenericDAO<employee,Integer> employeeDAO, GenericDAO<Project,Integer> taskDAO) {
        this.taskDAO = taskDAO;
        this.employeeDAO = employeeDAO;
        this.projectDAO = projectDAO;
    }

    public void createTask(String name, int projectId) {
        Task task = new Task();
        task.setName(name);
        task.setStatus("Non démarrée");
        Project project = projectDAO.getById(projectId);
        if (project != null) {
            task.setProject(project);
            taskDAO.save(task);
            if (project.getTasks() == null) {
                project.setTasks(new ArrayList<Task>());
            }
            project.getTasks().add(task);
            projectDAO.update(project);
        } else {
            System.out.println("Projet non trouvé.");
        }
    }

    public void updateTaskStatus(int taskId, String status) {
        Task task = taskDAO.getById(taskId);
        if (task != null) {
            task.setStatus(status);
            taskDAO.update(task);
        } else {
            System.out.println("Tâche non trouvée.");
        }
    }

    public void assignEmployeeToTask(int taskId, int employeeId) {
        Task task = taskDAO.getById(taskId);
        Employee employee = employeeDAO.getById(employeeId);
        if (task != null && employee != null) {
            task.setAssignedEmployee(employee);
            taskDAO.update(task);
        } else {
            System.out.println("Tâche ou employé non trouvé.");
        }
    }

    public void getAllTasks() {
        List<Task> tasks = taskDAO.getAllTasks();
        System.out.println("Liste des tâches :");
        for (Task t : tasks) {
            System.out.println("- " + t.getName() + " [Statut: " + t.getStatus() + "]");
        }
    }
}
