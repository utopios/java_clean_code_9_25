package correction;


import com.example.correctionapp.dao.TaskDAO;
import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.service.NotificationService;
import com.example.correctionapp.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class NotificationServiceTest {

    private NotificationService notificationService;
    private TaskDAO taskDAO;
    private Task task;
    private Employee employee;

    @BeforeEach
    void setUp() {
        taskDAO = mock(TaskDAO.class);
        notificationService = new NotificationServiceImpl(taskDAO);

        employee = new Employee("John Doe", "Developer", "john@test.com");
        employee.setId(1);

        Project project = new Project("Test Project", "Description");
        project.addEmployee(employee);

        task = new Task("Test Task", "Description");
        task.setId(1);
        task.setProject(project);
        task.assignTo(employee);
    }

    @Test
    @DisplayName("Should notify when task status changes")
    void shouldNotifyWhenTaskStatusChanges() {

        assertDoesNotThrow(() ->
                notificationService.notifyTaskStatusChanged(task, TaskStatus.TODO, TaskStatus.IN_PROGRESS));
    }

    @Test
    @DisplayName("Should notify when task is assigned")
    void shouldNotifyWhenTaskIsAssigned() {
        Employee newEmployee = new Employee("Jane Smith", "Designer", "jane@test.com");
        newEmployee.setId(2);

        assertDoesNotThrow(() ->
                notificationService.notifyTaskAssigned(task, null, newEmployee));
    }
}
