package org.example;
import java.util.List;
public class Portfolio {

    private final List<Investment> investments;
    private final String baseCurrency;

    public Portfolio(List<Investment> investments, String baseCurrency) {
        this.investments = investments;
        this.baseCurrency = baseCurrency;
    }

    public void addInvestment(Investment investment) {
        if (investment == null) {
            throw new IllegalArgumentException("L'investissement ne peut pas être null");
        }
        investments.add(investment);
    }

    public Money calculateTotalValue() {
        if (investments.isEmpty()) {
            return new Money(0, baseCurrency);
        }
        return investments.stream()
                .map(Investment::totalValue)
                .reduce(new Money(0, baseCurrency),Money::add);

    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public boolean isEmpty() {
        return investments.isEmpty();
    }
}
