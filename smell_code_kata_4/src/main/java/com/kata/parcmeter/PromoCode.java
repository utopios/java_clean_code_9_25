package com.kata.parcmeter;


public class PromoCode {
    private static final String VALID_PROMO = "PROMO10";
    private static final double DISCOUNT_RATE = 0.1;

    private final String code;

    private PromoCode(String code) {
        this.code = code;
    }

    public static PromoCode of(String code) {
        return new PromoCode(code);
    }

    public static PromoCode none() {
        return new PromoCode(null);
    }

    public boolean isValid() {
        return code != null && VALID_PROMO.equalsIgnoreCase(code);
    }

    public Price applyDiscount(Price price) {
        if (!isValid()) return price;
        return price.multiply(1.0 - DISCOUNT_RATE);
    }

    @Override
    public String toString() {
        return code != null ? code : "NO_PROMO";
    }
}
