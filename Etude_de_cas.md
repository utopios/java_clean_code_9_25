**Etude de cas : Refactoring et TDD en Java pour une Application de Gestion des Projets**

---

### **Contexte**

Votre entreprise utilise une application interne pour gérer les projets, les tâches associées et les ressources humaines impliquées. Cette application a été développée rapidement sans respecter les bonnes pratiques de développement. Elle est devenue difficile à maintenir, à tester et à étendre. Votre mission est de **refactoriser plusieurs parties du code existant** en appliquant les principes du **Clean Code** et les **Design Patterns** appropriés. De plus, vous devez développer de nouvelles fonctionnalités en utilisant le **Test-Driven Development (TDD)**.

### **Architecture de l'Application**

L'application suit une architecture en couches typique :

- **Couche de Présentation (UI)** : Interface utilisateur console ou web pour interagir avec l'application.
- **Couche Métier (Business Logic)** : Contient la logique métier pour la gestion des projets, des tâches et des employés.
- **Couche de Service** : Gère les règles métier plus complexes et orchestre les appels entre les différentes couches.
- **Couche d'Accès aux Données (DAO/Repository)** : Gère la persistance des données dans une base de données relationnelle.


### **Objectifs**

1. **Refactoriser le Code Existant** :
   - Appliquer les principes du **Clean Code** pour améliorer la lisibilité et la maintenabilité.
   - Utiliser des **Design Patterns** appropriés pour réduire le couplage et améliorer la flexibilité.
   - Respecter les principes **SOLID**.

2. **Développer de Nouvelles Fonctionnalités en TDD** :
   - Ajouter la fonctionnalité de suivi du statut des tâches (`À faire`, `En cours`, `Terminée`).
   - Permettre l'envoi de notifications aux employés assignés lorsqu'une tâche est mise à jour.
   - Utiliser le **Test-Driven Development (TDD)** pour implémenter ces fonctionnalités.

3. **Gérer les Exceptions** :
   - Ajouter une gestion appropriée des exceptions.
   - Créer des exceptions personnalisées si nécessaire,

# DAO

## Checklist Clean Code

#### Structure & Architecture
- [ ] Créer des interfaces pour tous les services
- [ ] Implémenter le pattern Repository correctement
- [ ] Ajouter injection de dépendance (Spring, Guice, ou manuel)
- [ ] Séparer la configuration de l'exécution

#### Gestion d'Erreurs
- [ ] Remplacer `null` par `Optional<T>`
- [ ] Créer des exceptions métier spécifiques
- [ ] Implémenter une stratégie de logging cohérente
- [ ] Ajouter validation des paramètres d'entrée

#### Testabilité
- [ ] Rendre les collections non-statiques
- [ ] Séparer la logique métier de l'infrastructure
- [ ] Ajouter des tests unitaires

#### Lisibilité
- [ ] Extraire des constantes pour les valeurs magiques
- [ ] Améliorer les noms de variables et méthodes
- [ ] Formater le code de manière cohérente