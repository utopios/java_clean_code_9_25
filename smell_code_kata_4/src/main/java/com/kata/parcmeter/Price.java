package com.kata.parcmeter;


import com.kata.parcmeter.exception.ParkingException;
import com.kata.parcmeter.log.ConsoleLogger;
import com.kata.parcmeter.log.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Price {
    private final BigDecimal amount;
    private static final Logger logger = new ConsoleLogger();

    private Price(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Price amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            logger.warn("Negative price detected: " + amount + ", setting to zero");
            this.amount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        } else {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static Price of(double value) {
        try {
            return new Price(BigDecimal.valueOf(value));
        } catch (Exception e) {
            //logger.error("Failed to create price from value: " + value, e);
            throw new ParkingException("Invalid price value: " + value, e);
        }
    }

    public static Price zero() {
        return new Price(BigDecimal.ZERO);
    }

    public Price multiply(double factor) {
        if (Double.isNaN(factor) || Double.isInfinite(factor)) {
            /*logger.error("Invalid multiplication factor: " + factor,
                    new IllegalArgumentException("Factor must be finite"));*/
            throw new ParkingException("Invalid multiplication factor: " + factor,  new IllegalArgumentException("Factor must be finite"));
        }
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
