Feature: Gestion de comptes

  Background:
    Given un utilisateur "admin" est connecté

  Scenario: Consultation du solde
    When il consulte son compte
    Then le solde doit s’afficher

  Scenario: Faire un virement
    When il effectue un virement de 100 EUR
    Then le solde est mis à jour

Feature: Gestion de connexion

    Scenario Outline: Connexion avec plusieurs utilisateurs
    Given un utilisateur "<login>" avec le mot de passe "<mdp>"
    When il tente de se connecter
    Then le résultat doit être "<resultat>"

    Examples:
    | login   | mdp    | resultat  |
    | admin   | 1234   | succès    |
    | user    | abcd   | succès    |
    | hacker  | 0000   | échec     |
