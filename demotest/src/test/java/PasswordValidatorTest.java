import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.example.entity.PasswordValidator;
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach()
    void setUp() {
        passwordValidator = new PasswordValidator();
    }
    @Test
    void shouldExist() {
        assertNotNull(passwordValidator);
    }

    @Test
    void shouldHaveIsValidMethod() {
        assertDoesNotThrow(() -> passwordValidator.isValid("test"));
    }

    @Test
    void shouldReturnTrueForPasswordWith8orMoreCharacters() {
        boolean result = passwordValidator.isValid("motdepasse");
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForPasswordWithLessThen8Characters() {
        boolean result = passwordValidator.isValid("court");
        assertFalse(result);
    }
}
