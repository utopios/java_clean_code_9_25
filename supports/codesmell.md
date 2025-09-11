## 1) Bloaters (code « obèse »)

### 1.1 Long Method (méthode trop longue)

**Symptômes** : trop d’étapes, multiples responsabilités, complexité cyclomatique élevée.
**Exemple**

```java
double total(Order o){ /* 80 lignes : parse, TVA, remises, I/O, logs... */ return 0; }
```

**Refactor** : Extract Method, Split Phase, Introduce Parameter Object.

### 1.2 Large Class (classe trop grosse)

**Symptômes** : trop d’attributs/méthodes hétérogènes, responsabilité multiple.
**Exemple**

```java
class OrderService { /* 1200 lignes : validation, taxes, e-mail, persistence... */ }
```

**Refactor** : Extract Class/Module, appliquer SRP.

### 1.3 Primitive Obsession (abus de types primitifs)

**Symptômes** : `String`/`int` à la place d’objets à invariants.
**Exemple**

```java
new Customer("John","0600000000","FR123456789"); // valeurs magiques
```

**Refactor** : Value Objects (`Phone`, `VatNumber`, `Money`), Encapsulate Record.

### 1.4 Long Parameter List (liste de paramètres trop longue)

**Symptômes** : >4–5 paramètres, difficile à lire et à appeler.
**Exemple**

```java
double price(double ht, double vat, double rem, String cur, ZoneId tz, Locale loc) { /*...*/ }
```

**Refactor** : Introduce Parameter Object, Builder, Preserve Whole Object.

### 1.5 Data Clumps (grappes de données)

**Symptômes** : paramètres/attributs qui voyagent toujours ensemble.
**Exemple**

```java
ship(street, city, zip, country);
```

**Refactor** : Extract Class (`Address`), Introduce Parameter Object.

### 1.6 Flag Argument (paramètre drapeau)

**Symptômes** : un booléen oriente des branches de logique.
**Exemple**

```java
generateReport(data, /*isCsv*/ true);
```

**Refactor** : séparer en deux méthodes explicites, Strategy.

### 1.7 Magic Numbers (nombres magiques)

**Symptômes** : constantes littérales inexpliquées.
**Exemple**

```java
double ttc = ht * 1.2; // 1.2 ?
```

**Refactor** : Introduce Constant (`VAT_FRANCE = 0.20`), Value Object.

---

## 2) Object-Orientation Abusers (mauvaise utilisation de la POO)

### 2.1 Switch Statements / Repeated Switches (conditionnels répétés)

**Symptômes** : `if/switch` sur type/état disséminés.
**Exemple**

```java
switch(type){ case GOLD: return base*0.8; case SILVER: return base*0.9; default: return base; }
```

**Refactor** : Replace Conditional with Polymorphism (Strategy, State).

### 2.2 Temporary Field (champ temporaire)

**Symptômes** : champ utilisé seulement dans certains cas/scénarios.
**Exemple**

```java
class Report { String tmpCsvPath; /* utile seulement si export CSV */ }
```

**Refactor** : passer en variable locale, Extract Class, Introduce Special Case.

### 2.3 Refused Bequest (héritage « refusé »)

**Symptômes** : sous-classe n’utilise pas ce qu’elle hérite.
**Exemple**

```java
class Square extends Rectangle { /* override contraints width/height... */ }
```

**Refactor** : remplacer héritage par composition/délégation, Collapse Hierarchy.

### 2.4 Alternative Classes with Different Interfaces

**Symptômes** : classes analogues, interfaces dissemblables.
**Exemple**

```java
cache.put(k,v); store.save(k,v);
```

**Refactor** : Unify Interface, Adapter.

---

## 3) Change Preventers (qui empêchent les changements)

### 3.1 Divergent Change (changement divergent)

**Symptômes** : une classe change pour des raisons multiples.
**Exemple**

```java
class Order { /* parse, règles TVA, PDF, e-mail, repo */ }
```

**Refactor** : Extract Class/Module, SRP, couche dédiée par préoccupation.

### 3.2 Shotgun Surgery (chirurgie au fusil)

**Symptômes** : petite évolution → modifications dispersées.
**Exemple** : changer une taxe → 12 fichiers touchés.
**Refactor** : regrouper le comportement (Move Method/Field), Facade.

### 3.3 Parallel Inheritance Hierarchies

**Symptômes** : créer une classe dans A impose d’en créer une dans B.
**Exemple** : `ReportA`, `ReportB` ↔ `RendererA`, `RendererB`.
**Refactor** : Strategy/Bridge, Factory, réduire couplage hiérarchique.

### 3.4 Hidden Temporal Coupling (couplage temporel caché)

**Symptômes** : appels à faire dans un ordre précis non exprimé.
**Exemple**

```java
service.init(); service.load(); service.run(); // ordre implicite
```

**Refactor** : regrouper en une seule opération, Builders, invariants explicites.

---

## 4) Dispensables (superflus)

### 4.1 Comments (mauvais commentaires)

**Symptômes** : commentaires qui pallient un code peu clair.
**Exemple**

```java
// applique 20% et arrondit
double bf(double base){ double r=base*0.8; return Math.round(r*100)/100.0; }
```

**Refactor** : code auto-explicite (Rename, Extract Method). Conserver les commentaires de décision (le “pourquoi”).

### 4.2 Duplicate Code (duplication)

**Symptômes** : blocs identiques/similaires.
**Exemple**

```java
double bf(double b){ return round(b*0.8); }
double loyal(double b){ return round(b*0.9); }
```

**Refactor** : Extract Method, Template Method, Strategy, DRY.

### 4.3 Lazy Class / Lazy Element (classe paresseuse)

**Symptômes** : trop peu de valeur ajoutée.
**Exemple**

```java
class DiscountHelper { double rate(){ return 0.2; } }
```

**Refactor** : Inline Class.

### 4.4 Data Class (sac de données)

**Symptômes** : getters/setters, pas de logique, invariants absents.
**Exemple**

```java
class Money { double amount; String currency; /* getters/setters */ }
```

**Refactor** : introduire comportements, invariants, méthodes métier.

### 4.5 Dead Code (code mort)

**Symptômes** : jamais exécuté/appelé.
**Exemple**

```java
void legacy(){ /* non référencé */ }
```

**Refactor** : suppression.

### 4.6 Speculative Generality (généralisation spéculative)

**Symptômes** : hooks/templates « au cas où ».
**Exemple**

```java
interface DataProvider<TContext, TOptions> { /* options jamais utilisées */ }
```

**Refactor** : Collapse Hierarchy, Inline, supprimer l’inessentiel.

---

## 5) Couplers (mauvais couplage)

### 5.1 Feature Envy

**Symptômes** : une méthode utilise surtout les données d’une autre classe.
**Exemple**

```java
String city = invoice.getCustomer().getAddress().getCity();
```

**Refactor** : Move Method vers la classe des données visées.

### 5.2 Inappropriate Intimacy / Insider Trading (intimité inappropriée)

**Symptômes** : classes dépendant des détails internes l’une de l’autre.
**Exemple**

```java
orderRepo.connection().raw().commit();
```

**Refactor** : Hide Delegate, limiter la visibilité, loi de Demeter.

### 5.3 Message Chains (chaînes d’appels)

**Symptômes** : `a().b().c().d()`.
**Exemple**

```java
var city = i.customer().address().city().name();
```

**Refactor** : Hide Delegate, Introduce Method, API plus orientée usage.

### 5.4 Middle Man (homme du milieu)

**Symptômes** : une classe ne fait que relayer.
**Exemple**

```java
class CustomerProxy { String city(){ return customer.address().city(); } }
```

**Refactor** : Remove Middle Man, appeler la cible réelle.

### 5.5 Incomplete Library Class (classe de bibliothèque incomplète)

**Symptômes** : API tierce ne permet pas le comportement souhaité → contorsions.
**Exemple**

```java
// duplication utilitaire autour d'une classe tierce non extensible
```

**Refactor** : Adapter/Decorator, wrapper local maîtrisé.

---

## 6) Nommage, données et état (catalogue moderne)

### 6.1 Mysterious Name (nom mystérieux)

**Symptômes** : noms non intentionnels (`doIt`, `data`, `d`).
**Exemple**

```java
double d(double x){ return x*1.2; }
```

**Refactor** : Rename explicite (`applyVat`).

### 6.2 Global Data (données globales)

**Symptômes** : état mutable accessible partout.
**Exemple**

```java
public static double TAX_RATE = 0.2;
```

**Refactor** : Encapsulate Variable, injection de dépendances, immutabilité.

### 6.3 Mutable Data (données mutables non contrôlées)

**Symptômes** : effets de bord, ordre d’exécution fragile.
**Exemple**

```java
money.amount = money.amount * 1.2;
```

**Refactor** : rendre immuable, méthodes pures, copies défensives.

### 6.4 Inconsistent Naming (noms incohérents)

**Symptômes** : mêmes concepts, terminologie différente.
**Exemple**

```java
getCustomer(), fetchClient(), findBuyer()
```

**Refactor** : vocabulaire ubiquitaire, conventions cohérentes.

### 6.5 Control Coupling via Return/Exceptions mal employés

**Symptômes** : valeurs de retour/flags pilotent des branches lointaines.
**Exemple**

```java
int status = process(); if(status==2){ /* branche spéciale */ }
```

**Refactor** : exceptions spécifiques, résultats typés (`Result`, `Either`), polymorphisme.

---

## 7) Exemples condensés de détection

* Méthodes > 20–30 lignes ou complexité cyclomatique > 10 : Long Method.
* > 4–5 paramètres, répétitions de primitives : Long Parameter List, Primitive Obsession.
* Groupes de variables répétés : Data Clumps.
* `if/switch` sur type/état répétés : Repeated Switches → Polymorphisme.
* Modifications dispersées pour un seul changement : Shotgun Surgery.
* Accès en chaîne `a.b().c().d()` : Message Chains.
* Méthode qui tire surtout les données d’ailleurs : Feature Envy.
* Classes de 1000+ lignes, responsabilités mélangées : Large Class, Divergent Change.
* Duplication visible : Duplicate Code.
* Variables globales modifiables : Global/Mutable Data.

---

## 8) Micro-exemples de refactor (avant → après)

### Replace Conditional with Polymorphism

```java
// Avant
double price(String type, double base){
  return switch(type){ case "GOLD" -> base*0.8; case "SILVER" -> base*0.9; default -> base; };
}
// Après
interface Pricing { double apply(double base); }
final class Gold implements Pricing { public double apply(double b){ return b*0.8; } }
final class Silver implements Pricing { public double apply(double b){ return b*0.9; } }
```

### Introduce Parameter Object / Value Object

```java
// Avant
Money total(double ht, double vat, double discount, String currency){ /*...*/ }
// Après
record Pricing(double vat, double discount) {}
Money total(Money base, Pricing pricing){ /*...*/ }
```

### Extract Class / Hide Delegate (loi de Demeter)

```java
// Avant
String city = invoice.getCustomer().getAddress().getCity();
// Après
class Address { String city(){...} }
class Customer { Address address; String city(){ return address.city(); } }
class Invoice { Customer customer; String city(){ return customer.city(); } }
```

---

## 9) Outils et métriques utiles

* Détecteurs statiques : SonarQube, PMD, Checkstyle, SpotBugs (duplications, complexité, longues méthodes/listes de paramètres, champs inutilisés).
* Indicateurs simples : nombre de lignes par classe/méthode, complexité cyclomatique, profondeur d’appels, fan-in/fan-out, taux de duplication.
* Pratiques : SRP, immutabilité, Value Objects, DI, tests de caractérisation avant refactor, commits atomiques par mouvement (Rename, Move Method, Extract, Inline).

