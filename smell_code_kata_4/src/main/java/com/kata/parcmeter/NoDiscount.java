package com.kata.parcmeter;

public class NoDiscount implements DiscountCalculator {
    @Override
    public Price apply(Price basePrice) {
        return basePrice;
    }
}
