package correction;

import com.example.correctionapp.entity.Employee;
import com.example.correctionapp.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;
    @BeforeEach
    void setUp() {
        employee = new Employee("John Doe", "Developer", "john@test.com");
        employee.setId(1);
    }

    @Test
    @DisplayName("Employee can take more tasks when under limit")
    void canTakeMoreTasksWhenUnderLimit() {
        assertTrue(employee.canTakeMoreTasks());
    }

    @Test
    @DisplayName("Employee validation works properly")
    void employeeValidationWorks() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("", "Developer", "test@test.com"));
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "", "test@test.com"));
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Developer", "invalid-email"));
    }

    @Test
    @DisplayName("hasRole method works correctly")
    void hasRoleWorksCorrectly() {
        assertTrue(employee.hasRole("Developer"));
        assertTrue(employee.hasRole("developer"));
        assertFalse(employee.hasRole("Manager"));
    }
}
