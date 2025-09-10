Feature: Retrait d'argent au distributeur
  Scenario: Retrait avec solde suffisant
    Given mon compte a un solde de 100 euros
    When je demande à retirer 50 euros
    Then je devrais recevoir 50 euros
    And mon solde devrait être de 50 euros