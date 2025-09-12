package correction;


import com.example.correctionapp.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    @DisplayName("TODO peut transitionner vers IN_PROGRESS")
    void todoCanTransitionToInProgress() {
        assertTrue(TaskStatus.TODO.canTransitionTo(TaskStatus.IN_PROGRESS));
    }

    @Test
    @DisplayName("TODO ne peut pas transitionner directement vers DONE")
    void todoCannotTransitionDirectlyToDone() {
        assertFalse(TaskStatus.TODO.canTransitionTo(TaskStatus.DONE));
    }

    @Test
    @DisplayName("IN_PROGRESS peut transitionner vers DONE ou TODO")
    void inProgressCanTransitionToDoneOrTodo() {
        assertTrue(TaskStatus.IN_PROGRESS.canTransitionTo(TaskStatus.DONE));
        assertTrue(TaskStatus.IN_PROGRESS.canTransitionTo(TaskStatus.TODO));
    }

    @Test
    @DisplayName("DONE ne peut transitionner vers aucun autre statut")
    void doneCannotTransitionToAnyStatus() {
        assertFalse(TaskStatus.DONE.canTransitionTo(TaskStatus.TODO));
        assertFalse(TaskStatus.DONE.canTransitionTo(TaskStatus.IN_PROGRESS));
        assertFalse(TaskStatus.DONE.canTransitionTo(TaskStatus.DONE));
    }

    @Test
    @DisplayName("Display names sont corrects")
    void displayNamesAreCorrect() {
        assertEquals("À faire", TaskStatus.TODO.getDisplayName());
        assertEquals("En cours", TaskStatus.IN_PROGRESS.getDisplayName());
        assertEquals("Terminée", TaskStatus.DONE.getDisplayName());
    }
}
