## Progression BDD

### Phase 1: Conversation métier (3 Amigos)

- **PO**: je veux qu'un client retire de l'argent
- **Dev**: Quelles règles ? Limites ?...
- **Testeur**: Et si pas assez d'argent ? et si la carte expirée ?

**Résultat :** Accord sur le ou les premiers scénario.

### Phase 2: Itération scénario (langage métier)

```gherkin
# Ficher: test/resources/features/withdraw_money.feature
Feature: Retrait d'argent au distributeur
    Scenario: Retrait avec solde suffisant
    Given mon compte a un solde de 100 euros
    When je demande à retirer 50 euros
    Then je devrais recevoir 50 euros
    And mon solde devrait être de 50 euros
```

### Phase 3: Exécution et Echec des Test

### Phase 4: Emerger la conception de l'application