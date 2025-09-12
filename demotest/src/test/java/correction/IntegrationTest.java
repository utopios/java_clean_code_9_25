package correction;

import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.exception.*;
import com.example.correctionapp.service.EmployeeService;
import com.example.correctionapp.service.ProjectService;
import com.example.correctionapp.service.ServiceFactory;
import com.example.correctionapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private ServiceFactory serviceFactory;
    private EmployeeService employeeService;
    private ProjectService projectService;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        serviceFactory = ServiceFactory.getInstance();
        employeeService = serviceFactory.getEmployeeService();
        projectService = serviceFactory.getProjectService();
        taskService = serviceFactory.getTaskService();
    }

    @Test
    @DisplayName("Complete workflow integration test")
    void completeWorkflowTest() {
        Project project = projectService.createProject("Integration Test", "Test complet");
        assertNotNull(project.getId());

        Employee dev = employeeService.addEmployee("John Dev", "Developer", "john@test.com");
        Employee qa = employeeService.addEmployee("Jane QA", "QA", "jane@test.com");

        projectService.assignEmployeeToProject(project.getId(), dev.getId());
        projectService.assignEmployeeToProject(project.getId(), qa.getId());

        Project updatedProject = projectService.findProjectById(project.getId()).orElseThrow();
        assertEquals(2, updatedProject.getEmployeeCount());
        assertTrue(updatedProject.hasEmployee(dev));
        assertTrue(updatedProject.hasEmployee(qa));

        Task devTask = taskService.createTask("Development Task", "Code feature", project.getId());
        Task qaTask = taskService.createTask("QA Task", "Test feature", project.getId());

        taskService.assignEmployeeToTask(devTask.getId(), dev.getId());
        taskService.assignEmployeeToTask(qaTask.getId(), qa.getId());

        assertEquals(TaskStatus.TODO, devTask.getStatus());

        taskService.updateTaskStatus(devTask.getId(), TaskStatus.IN_PROGRESS);
        Task updatedDevTask = taskService.findTaskById(devTask.getId()).orElseThrow();
        assertEquals(TaskStatus.IN_PROGRESS, updatedDevTask.getStatus());

        taskService.updateTaskStatus(devTask.getId(), TaskStatus.DONE);
        updatedDevTask = taskService.findTaskById(devTask.getId()).orElseThrow();
        assertEquals(TaskStatus.DONE, updatedDevTask.getStatus());

        taskService.updateTaskStatus(qaTask.getId(), TaskStatus.IN_PROGRESS);
        Task updatedQaTask = taskService.findTaskById(qaTask.getId()).orElseThrow();
        assertEquals(TaskStatus.IN_PROGRESS, updatedQaTask.getStatus());

        Project finalProject = projectService.findProjectById(project.getId()).orElseThrow();
        assertEquals(50.0, finalProject.getCompletionPercentage());

        assertEquals(1, taskService.findTasksByStatus(TaskStatus.DONE).size());
        assertEquals(1, taskService.findTasksByStatus(TaskStatus.IN_PROGRESS).size());

    }

    @Test
    @DisplayName("Error handling integration test")
    void errorHandlingIntegrationTest() {

        assertThrows(ProjectNotFoundException.class,
                () -> taskService.createTask("Task", 999));

        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTaskStatus(999, TaskStatus.DONE));

        assertThrows(EmployeeNotFoundException.class,
                () -> taskService.assignEmployeeToTask(1, 999));
    }

    @Test
    @DisplayName("Business rules integration test")
    void businessRulesIntegrationTest() {
        Project project = projectService.createProject("Business Rules Test", "Test des règles métier");
        Employee employee = employeeService.addEmployee("Test Employee", "Developer", "test@test.com");
        projectService.assignEmployeeToProject(project.getId(), employee.getId());

        Employee outsideEmployee = employeeService.addEmployee("Outside Employee", "Developer", "outside@test.com");
        Task task = taskService.createTask("Test Task", "Description", project.getId());

        assertThrows(TaskAssignmentException.class,
                () -> taskService.assignEmployeeToTask(task.getId(), outsideEmployee.getId()));

        for (int i = 0; i < 5; i++) {
            Task limitTask = taskService.createTask("Task " + i, "Description", project.getId());
            taskService.assignEmployeeToTask(limitTask.getId(), employee.getId());
        }

        Task extraTask = taskService.createTask("Extra Task", "Description", project.getId());
        assertThrows(TaskAssignmentException.class,
                () -> taskService.assignEmployeeToTask(extraTask.getId(), employee.getId()));

        assertThrows(InvalidTaskStatusTransitionException.class,
                () -> taskService.updateTaskStatus(task.getId(), TaskStatus.DONE));
    }
}
