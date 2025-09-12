package com.example.correctionapp.service;


import com.example.correctionapp.dao.EmployeeDAO;
import com.example.correctionapp.dao.ProjectDAO;
import com.example.correctionapp.dao.TaskDAO;
import com.example.correctionapp.service.impl.NotificationServiceImpl;
import com.example.correctionapp.service.impl.TaskServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance;
    private final EmployeeDAO employeeDAO;
    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;

    // Services
    private final NotificationService notificationService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final TaskService taskService;

    private ServiceFactory() {
        this.employeeDAO = new EmployeeDAO();
        this.projectDAO = new ProjectDAO();
        this.taskDAO = new TaskDAO();

        this.notificationService = new NotificationServiceImpl(taskDAO);
        this.employeeService = new EmployeeService(employeeDAO);
        this.projectService = new ProjectService(projectDAO, employeeDAO);
        this.taskService = new TaskServiceImpl(taskDAO, projectDAO, employeeDAO, notificationService);
    }

    public static synchronized ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public static ServiceFactory createWithMocks(EmployeeDAO employeeDAO,
                                                 ProjectDAO projectDAO,
                                                 TaskDAO taskDAO,
                                                 NotificationService notificationService) {
        ServiceFactory factory = new ServiceFactory();
        return factory;
    }
}
