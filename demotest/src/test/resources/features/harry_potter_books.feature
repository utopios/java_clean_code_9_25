Feature: Calcul du prix des livres Harry Potter
  En tant que client de la librairie
  Je veux acheter des livres Harry Potter
  Afin de bénéficier des meilleures remises possibles

  Scenario: Achat d'un seul livre
    Given je veux acheter des livres Harry Potter
    When j'ajoute 1 exemplaire du tome 1 à mon panier
    Then le prix total devrait être de 8.0 euros