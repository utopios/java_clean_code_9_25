package org.example;


import java.util.Objects;

public final class Money {
    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être un nombre positif");
        }
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("La devise doit être une chaîne de caractères non vide");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public Money convert(String newCurrency, double exchangeRate) {
        return new Money(amount * exchangeRate, newCurrency);
    }

    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Les devises doivent être identiques pour additionner les montants");
        }
        return new Money(amount + other.amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }


    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
