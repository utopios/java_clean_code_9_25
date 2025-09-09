### Kata : Gestion de Portefeuille avec Value Objects

#### Contexte :
Vous allez implémenter un portefeuille d’investissement. Ce portefeuille contient plusieurs types d'**Investments** (des actions, des obligations, etc.). Chaque type d'investissement est représenté par un **Value Object**. Ces objets doivent être immuables et garantir la validité des données. Vous allez aussi calculer des opérations financières en parallèle, comme le total des investissements, tout en garantissant que le code reste propre et simple à maintenir.

#### Étapes :


### Étape 1 : Créer un **Value Object** pour représenter la monnaie

1. **Créer la classe `Money`** :
   - Elle doit encapsuler un montant (`amount`) et une devise (`currency`).
   - Le montant doit être un nombre positif.
   - La devise doit être une chaîne de caractères non vide.
   - La classe doit être immuable.

2. **Ajouter les fonctionnalités suivantes** :
   - Une méthode pour convertir la monnaie en une autre devise (utilisez un taux de change fixe pour l’exercice).
   - Une méthode `add(Money)` pour additionner deux montants si les devises sont identiques.
   - Implémenter les méthodes `equals` et `hashCode` pour comparer deux objets `Money` basés sur leurs valeurs.


### Étape 2 : Créer un **Value Object** pour représenter un **Investment**

1. **Créer la classe `Investment`** :
   - Cette classe représente un investissement avec un nom (`name`), une quantité (`quantity`), et un montant investi (`Money`).

2. **Ajouter des fonctionnalités** :
   - Une méthode `totalValue()` qui calcule la valeur totale de l’investissement (`quantity * amount`).
   - Une méthode pour comparer deux investissements basés sur leur valeur totale.



### Étape 3 : Gérer un **portefeuille d'investissements**

1. **Créer la classe `Portfolio`** :
   - Cette classe contient une liste d’objets `Investment`.
   - Ajouter des méthodes pour :
     - Ajouter un investissement.
     - Calculer la valeur totale du portefeuille.
     - Afficher tous les investissements.


