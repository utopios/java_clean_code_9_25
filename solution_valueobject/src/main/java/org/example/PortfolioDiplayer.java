package org.example;
import java.util.List;
public class PortfolioDiplayer {

    public void displayAllInvestments(Portfolio portfolio) {
        if (portfolio == null) {
            throw new IllegalArgumentException("Le portefeuille ne peut pas être null");
        }

        if (portfolio.isEmpty()) {
            displayEmptyPortfolio();
            return;
        }

        displayPortfolioHeader();
        displayInvestmentList(portfolio.getInvestments());
        displayPortfolioFooter(portfolio.calculateTotalValue());
    }

    private void displayEmptyPortfolio() {
        System.out.println("Le portefeuille est vide.");
    }
    private void displayPortfolioHeader() {
        System.out.println("=== PORTEFEUILLE D'INVESTISSEMENTS ===");
    }

    private void displayInvestmentList(List<Investment> investments) {
        investments.forEach(System.out::println);
    }

    private void displayPortfolioFooter(Money totalValue) {
        System.out.println("--------------------------------------");
        System.out.println("Valeur totale: " + totalValue);
    }
}
