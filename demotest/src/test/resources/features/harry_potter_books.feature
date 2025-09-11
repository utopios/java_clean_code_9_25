Feature: Calcul du prix des livres Harry Potter
  En tant que client de la librairie
  Je veux acheter des livres Harry Potter
  Afin de bénéficier des meilleures remises possibles

  Scenario: Achat d'un seul livre
    Given je veux acheter des livres Harry Potter
    When j'ajoute 1 exemplaire du tome 1 à mon panier
    Then le prix total devrait être de 8.0 euros

  Scenario: Achat de 2 livres différents avec remise de 5%
    Given je veux acheter des livres Harry Potter
    When j'ajoute 1 exemplaire du tome 1 à mon panier
    And j'ajoute 1 exemplaire du tome 2 à mon panier
    Then le prix total devrait être de 15.2 euros
    And la remise appliquée est de 5%

  Scenario: Achat avec des exemplaires en double
    Given je veux acheter des livres Harry Potter
    When j'ajoute 2 exemplaires du tome 1 à mon panier
    And j'ajoute 1 exemplaire du tome 2 à mon panier
    Then le prix total devrait être de 23.2 euros
    And la remise appliquée est de 5%

  Scenario: Tous les 5 tomes différents
    Given je veux acheter des livres Harry Potter
    When j'ajoute 1 exemplaire du tome 1 à mon panier
    And j'ajoute 1 exemplaire du tome 2 à mon panier
    And j'ajoute 1 exemplaire du tome 3 à mon panier
    And j'ajoute 1 exemplaire du tome 4 à mon panier
    And j'ajoute 1 exemplaire du tome 5 à mon panier
    Then le prix total devrait être de 30.0 euros
    And la remise appliquée est de 25%

  Scenario: Cas complexe d'optimisation
    Given je veux acheter des livres Harry Potter
    When j'ajoute 2 exemplaires du tome 1 à mon panier
    And j'ajoute 2 exemplaires du tome 2 à mon panier
    And j'ajoute 2 exemplaires du tome 3 à mon panier
    And j'ajoute 1 exemplaire du tome 4 à mon panier
    And j'ajoute 1 exemplaire du tome 5 à mon panier
    Then le prix total devrait être de 51.6 euros
    And la remise appliquée est de 25%

  Scenario: Panier vide
    Given je veux acheter des livres Harry Potter
    When je ne mets aucun livre dans mon panier
    Then le prix total devrait être de 0.0 euros