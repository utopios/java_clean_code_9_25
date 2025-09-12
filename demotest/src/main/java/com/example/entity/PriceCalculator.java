package com.example.entity;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PriceCalculator {
    private static final double BOOK_PRICE = 8.0;

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.0,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );
    public double calculatePrice(BookBasket bookBasket) {
        List<Integer> books = bookBasket.getBooks();

        Set<Integer> uniqueBooks = new HashSet<>(books);
        int numberOfDifferentBooks = uniqueBooks.size();

        double totalPrice = books.size() * BOOK_PRICE;
        double discount = DISCOUNTS.getOrDefault(numberOfDifferentBooks, 0.0);

        return totalPrice * (1.0 - discount);
    }

    public int getRate(BookBasket bookBasket) {
        return 0;
    }
}
