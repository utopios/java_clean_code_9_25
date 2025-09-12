package correction;

import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Project;
import com.example.correctionapp.entity.Task;
import com.example.correctionapp.entity.TaskStatus;
import com.example.correctionapp.exception.InvalidTaskStatusTransitionException;
import com.example.correctionapp.exception.TaskAssignmentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;
    private Employee employee;
    private Project project;

    @BeforeEach
    void setUp() {
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
    @DisplayName("Nouvelle tâche a le statut TODO par défaut")
    void newTaskHasTodoStatusByDefault() {
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    @DisplayName("Peut assigner un employé à une tâche")
    void canAssignEmployeeToTask() {
        task.assignTo(employee);
        assertEquals(employee, task.getAssignedEmployee());
        assertTrue(employee.getAssignedTasks().contains(task));
    }

    @Test
    @DisplayName("Cannot assign employee not in project")
    void cannotAssignEmployeeNotInProject() {
        Employee outsideEmployee = new Employee("Jane Smith", "Designer", "jane@test.com");
        outsideEmployee.setId(2);

        assertThrows(TaskAssignmentException.class, () -> task.assignTo(outsideEmployee));
    }

    @Test
    @DisplayName("Peut changer le statut de TODO à IN_PROGRESS")
    void canChangeStatusFromTodoToInProgress() {
        task.updateStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    @DisplayName("Cannot change status from TODO directly to DONE")
    void cannotChangeStatusFromTodoDirectlyToDone() {
        assertThrows(InvalidTaskStatusTransitionException.class,
                () -> task.updateStatus(TaskStatus.DONE));
    }

    @Test
    @DisplayName("Cannot assign to completed task")
    void cannotAssignToCompletedTask() {
        task.updateStatus(TaskStatus.IN_PROGRESS);
        task.updateStatus(TaskStatus.DONE);

        assertThrows(TaskAssignmentException.class, () -> task.assignTo(employee));
    }

    @Test
    @DisplayName("Task validation works properly")
    void taskValidationWorks() {
        assertThrows(IllegalArgumentException.class, () -> task.setName(""));
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
    }
}

