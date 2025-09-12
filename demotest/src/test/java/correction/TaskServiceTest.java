package correction;


import com.example.correctionapp.dao.EmployeeDAO;
import com.example.correctionapp.dao.ProjectDAO;
import com.example.correctionapp.dao.TaskDAO;
import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.exception.EmployeeNotFoundException;
import com.example.correctionapp.exception.ProjectNotFoundException;
import com.example.correctionapp.exception.TaskNotFoundException;
import com.example.correctionapp.service.NotificationService;
import com.example.correctionapp.service.TaskService;
import com.example.correctionapp.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskDAO taskDAO;

    @Mock
    private ProjectDAO projectDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private NotificationService notificationService;

    private TaskService taskService;
    private Project project;
    private Employee employee;
    private Task task;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(taskDAO, projectDAO, employeeDAO, notificationService);

        project = new Project("Test Project", "Description");
        project.setId(1);

        employee = new Employee("John Doe", "Developer", "john@test.com");
        employee.setId(1);
        project.addEmployee(employee);

        task = new Task("Test Task", "Description");
        task.setId(1);
        task.setProject(project);
    }

    @Test
    @DisplayName("Should create task successfully")
    void shouldCreateTaskSuccessfully() {
        // Given
        when(projectDAO.findById(1)).thenReturn(Optional.of(project));

        // When
        Task createdTask = taskService.createTask("New Task", "Description", 1);

        // Then
        assertNotNull(createdTask);
        assertEquals("New Task", createdTask.getName());
        assertEquals(TaskStatus.TODO, createdTask.getStatus());
        verify(taskDAO).save(any(Task.class));
        verify(projectDAO).update(project);
    }

    @Test
    @DisplayName("Should throw exception when project not found")
    void shouldThrowExceptionWhenProjectNotFound() {

        when(projectDAO.findById(999)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class,
                () -> taskService.createTask("New Task", 999));
    }

    @Test
    @DisplayName("Should update task status and send notification")
    void shouldUpdateTaskStatusAndSendNotification() {

        task.assignTo(employee);
        when(taskDAO.findById(1)).thenReturn(Optional.of(task));


        taskService.updateTaskStatus(1, TaskStatus.IN_PROGRESS);


        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        verify(taskDAO).update(task);
        verify(notificationService).notifyTaskStatusChanged(task, TaskStatus.TODO, TaskStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("Should assign employee to task")
    void shouldAssignEmployeeToTask() {

        when(taskDAO.findById(1)).thenReturn(Optional.of(task));
        when(employeeDAO.findById(1)).thenReturn(Optional.of(employee));


        taskService.assignEmployeeToTask(1, 1);


        assertEquals(employee, task.getAssignedEmployee());
        verify(taskDAO).update(task);
        verify(notificationService).notifyTaskAssigned(task, null, employee);
    }

    @Test
    @DisplayName("Should throw exception when task not found for assignment")
    void shouldThrowExceptionWhenTaskNotFoundForAssignment() {

        when(taskDAO.findById(999)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class,
                () -> taskService.assignEmployeeToTask(999, 1));
    }

    @Test
    @DisplayName("Should throw exception when employee not found for assignment")
    void shouldThrowExceptionWhenEmployeeNotFoundForAssignment() {

        when(taskDAO.findById(1)).thenReturn(Optional.of(task));
        when(employeeDAO.findById(999)).thenReturn(Optional.empty());


        assertThrows(EmployeeNotFoundException.class,
                () -> taskService.assignEmployeeToTask(1, 999));
    }
}
