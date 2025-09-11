package com.kata.parcmeter;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Price {
    private final BigDecimal amount;

    private Price(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Price of(double value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price zero() {
        return new Price(BigDecimal.ZERO);
    }

    public Price multiply(double factor) {
        return new Price(amount.multiply(BigDecimal.valueOf(factor)));
    }

    public Price add(Price other) {
        return new Price(amount.add(other.amount));
    }

    public Price min(Price other) {
        return amount.compareTo(other.amount) <= 0 ? this : other;
    }

    public double asDouble() {
        return amount.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amount, price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount + "€";
    }
}
