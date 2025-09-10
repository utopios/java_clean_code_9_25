import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.example.entity.SmartRefrigerator;
import org.example.entity.FoodItem;

import java.time.LocalDateTime;
import java.util.List;
public class SmartRefrigeratorTest {
    @Test
    void shouldBeEmptyWhenCreated() {
        SmartRefrigerator fridge = new SmartRefrigerator();
        assertTrue(fridge.isEmpty());
    }

    @Test
    void shouldNotBeEmptyAfterAddingAnItem() {
        SmartRefrigerator fridge = new SmartRefrigerator();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(3);
        String nameItem = "Lait";

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
        SmartRefrigerator fridge = new SmartRefrigerator();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(3);
        String nameItem = "Lait";

        fridge.addItem(nameItem, expirationDateTime);

        List<FoodItem> items = fridge.getItems();
        FoodItem foodItem = items.get(0);

        assertEquals(nameItem, foodItem.getNameItem());
        assertEquals(expirationDateTime, foodItem.getexpirationDateTime());
    }
}
