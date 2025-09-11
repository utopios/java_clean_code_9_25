import org.example.entity.BookBasket;
import org.example.entity.PriceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class PriceCalculatorTest {
    private PriceCalculator calculator;
    private BookBasket basket;
    @BeforeEach
    void setUp() {
        calculator = new PriceCalculator();
        basket = new BookBasket();
    }

    @Test
    void shouldCalculateZeroForEmptyBasket() {
        double price = calculator.calculatePrice(basket);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void shouldCalculate8EurosForOneBook() {
        basket.addBook(1);
        double price = calculator.calculatePrice(basket);
        assertThat(price).isEqualTo(8.0);
    }

    @Test
    void shouldApply5PercentDiscountForTwoDifferentBooks() {
        basket.addBook(1);
        basket.addBook(2);
        double price = calculator.calculatePrice(basket);
        assertThat(price).isEqualTo(15.2);
    }

    @Test
    void shouldOptimizeComplexCase() {
        basket.addBook(1); basket.addBook(1);
        basket.addBook(2); basket.addBook(2);
        basket.addBook(3); basket.addBook(3);
        basket.addBook(4);
        basket.addBook(5);

        double price = calculator.calculatePrice(basket);
        assertThat(price).isEqualTo(51.6);
    }
}
