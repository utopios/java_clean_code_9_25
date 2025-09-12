import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.entity.SmartRefrigerator;
import com.example.entity.FoodItem;

import java.time.LocalDateTime;
import java.util.List;
public class SmartRefrigeratorTest {

    private SmartRefrigerator fridge;
    private LocalDateTime expirationDateTime;
    private String nameItem;
    private List<FoodItem> items;
    private FoodItem foodItem;

    @BeforeEach()
    void setUp() {
        fridge = new SmartRefrigerator();
    }

    @Test
    void shouldBeEmptyWhenCreated() {
        assertTrue(fridge.isEmpty());
    }

    @Test
    void shouldNotBeEmptyAfterAddingAnItem() {

        expirationDateTime = LocalDateTime.now().plusDays(3);
        nameItem = "Lait";

        fridge.addItem(nameItem, expirationDateTime);

        assertFalse(fridge.isEmpty());
    }

    /*@Test
    void shouldReturnAddedItems() {
        SmartRefrigerator fridge = new SmartRefrigerator();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(3);
        String nameItem = "Lait";

        fridge.addItem(nameItem, expirationDateTime);

        List<FoodItem> items = fridge.getItems();
        assertEquals(1, items.size());
    }*/

    @Test
    void shouldStoreFoodItemAddedItems() {
        fridge = new SmartRefrigerator();
        expirationDateTime = LocalDateTime.now().plusDays(3);
        nameItem = "Lait";

        fridge.addItem(nameItem, expirationDateTime);

        items = fridge.getItems();
        foodItem = items.get(0);

        assertEquals(nameItem, foodItem.getNameItem());
        assertEquals(expirationDateTime, foodItem.getexpirationDateTime());
    }
}
