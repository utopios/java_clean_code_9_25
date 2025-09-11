1. **Petits pas + tests verts**
   *Cycle*: Red -> Green -> Refactor, ex. on implemente `total()` minimal pour faire passer 1 test, puis on nettoie.
2. **Supprimer le code mort**

   ```java
   // ✗ mort
   private void oldCalc() {} // jamais appelée → supprimer
   ```
3. **Éliminer la duplication (DRY)**
   Deux `if (status==PAID)` répétés → extraire `boolean isPaid(Order o)`.
4. **Code simple qui passe les tests**
   Commencer par addition simple dans `total()` avant d’introduire remises/composants.
5. **Dépendances explicites**

   ```java
   class PaymentService {
     PaymentGateway gateway; // injecté par constructeur
     PaymentService(PaymentGateway g){ this.gateway=g; }
   }
   ```
6. **Rapprocher ce qui change ensemble (cohésion)**
   Mettre `calculateTax` et `applyTax` dans `TaxService`, pas éparpillés.
7. **Séparer raisons de changement**
   Prix (métier) vs persistance (technique) : `Order` vs `OrderRepository`.
8. **Abstraction la plus simple**

   ```java
   interface DiscountPolicy { Money apply(Money subtotal); }
   ```
9. **Lisibilité avant optim**
   Remplacer `for` cryptique par `stream().map().sum()` si plus clair.
10. **Pas d’optim prématurée**
    N’ajoute pas de cache tant que la perf n’est pas un problème mesuré.

## Nommage

11. **Noms qui disent l’intention**
    `expireUnpaidOrders()` > `process()`.
12. **Pas d’abréviations**
    `customerRepository` > `custRepo`.
13. **Même concept, même nom**
    Toujours `subtotal`, pas `sum` ailleurs.
14. **Éviter noms génériques**
    `retryCount` > `n`.
15. **Prononçable/recherchable**
    `authorizationToken` > `authTok`.
16. **Classes = noms / Méthodes = verbes**
    `OrderValidator` / `validateOrder()`.

## Fonctions

17. **Petites fonctions**
    Extraire `validateItems()`, `validateAddress()` d’un gros `validate()`.
18. **Même niveau d’abstraction**
    Une méthode “haute-niveau” n’appelle pas `Collections.sort()` en plein milieu : extraire `sortItems()`.
19. **≤ 3 paramètres, pas de booléen**
    `ship(Order order, Address to)` au lieu de `ship(order, to, true)`.
20. **Pas d’effets de bord cachés**
    `priceWithTax(order)` ne doit pas modifier `order`.
21. **Pas de “out params”**
    Retourner `Result` plutôt que remplir un `List` passé en paramètre.
22. **Éviter switch répété**
    Remplacer `switch(type)` par `Map<Type, DiscountPolicy>`.
23. **Exceptions plutôt que codes d’erreur**
    `throw new PaymentDeclinedException()` au lieu de `return -1`.
24. **Éliminer duplication**
    Deux calculs de TVA identiques → extraire `tva(Money)`.
25. **Conditions positives et simples**
    `if (order.isEmpty()) return;` (early return) et `if (isEligible())`.
26. **Pas commenter une fonction trop longue : la réduire**
    Scinder en sous-méthodes nommées.

## Commentaires

27. **Préférer code clair**
    Renommer `x` en `retryAttempts` au lieu d’un commentaire explicatif.
28. **Commentaires d’intention/contextes**
    `// Règle métier: remises non cumulables`
29. **Pas de commentaires mensongers**
    Supprimer doc obsolète “retourne -1 si erreur”.
30. **Éviter le bruit**
    Pas de bannières ASCII, ni “// getter”.
31. **TODO/FIXME gérés**
    `// TODO(2025-09): supprimer la règle de transition prix`
32. **Doc API utile**
    Javadoc sur méthode publique exposée aux tiers.

## Formatage

33. **Ordre logique**
    Champs, constructeur, publics, privés… groupés.
34. **Lignes courtes, indentation cohérente**
    Respect du formatter (Spotless/Google Style).
35. **Un concept par ligne**
    Pas de 3 appels imbriqués sur la même ligne.
36. **Espaces pour séparer les idées**
    Laisser une ligne blanche entre sections logiques.

## Objets & structures

37. **Cacher les données**
    `order.addItem(item)` plutôt que `order.items.add(item)`.
38. **Objets = comportement**
    `Money.add(Money)` plutôt qu’exposer `amount` public.
39. **Éviter modèle anémique**
    Logique de remise dans `Order`/`DiscountPolicy`, pas dans `OrderService` seul.
40. **Types explicites**
    `Money` type dédié vs `BigDecimal` partout.
41. **Loi de Déméter**
    `order.customer().address().city()` → `order.shipCity()`.

## Gestion des erreurs

42. **Exceptions > codes**
    `Optional<Order> find(id)` + `orElseThrow(NotFound::new)`.
43. **Pas de catch vide**

    ```java
    try { pay(); } catch (IOException e) { log.error(...); throw e; }
    ```
44. **Messages utiles**
    `throw new IllegalStateException("Order #"+id+" already paid")`
45. **Éviter null**
    Retourner `Optional<Discount>`; accepter `Optional<PromoCode>`.
46. **Séparer logique/erreurs**
    Méthode `mapError(e)` dédiée, pas mélangé au calcul.

## Limites & intégrations

47. **Isoler la lib tierce**
    `StripeGateway implements PaymentGateway` masque Stripe SDK.
48. **Tests aux frontières**
    Tester `StripeGateway` avec un fake du SDK, contrats clairs.
49. **Limiter API externes au domaine**
    Le domaine consomme `PaymentGateway`, pas `StripeClient`.

## Tests

50. **FIRST**
    Tests rapides, hermétiques (HBM2DDL, WireMock, etc.).
51. **Un test = une raison d’échouer**
    `should_apply_20pct_discount_when_vip()`.
52. **TDD cycles**
    Écrire test échec → impl minimale → refactor.
53. **DRY dans tests**
    Builder `OrderBuilder.v1().withItem(...)`.
54. **Nommer Given/When/Then**
    `givenVIP_whenCheckout_thenApply20pct()`.
55. **Happy + erreur; pas trop de mocks**
    Mock `PaymentGateway`, pas `Money`.

## Classes

56. **Petites, cohésives**
    `Order`, `TaxService`, `PaymentService` au lieu d’un `OrderManager` géant.
57. **Dépendre d’abstractions**
    `InventoryPort` plutôt que `JdbcInventory`.
58. **Moins d’état; immuabilité**
    `record Money(BigDecimal amount, Currency c) {}`.
59. **LSP/ISP**
    Interface `Notifier { notify(Order) }` pas 10 méthodes inutiles.
60. **Builder/factory**
    `Order order = Order.builder().customer(c).item(i).build();`.

## Systèmes & architecture

61. **Composition root**
    Câbler les dépendances dans `Main`/Spring config, pas dans le domaine.
62. **DTSTTCPW**
    Commencer par `List` en mémoire avant d’introduire Kafka.
63. **Règles métier au centre**
    Hexagonal: domain core + ports/adapters (DB/Web).
64. **Couplage minimal**
    Publier `OrderPlaced` (événement), pas l’entité entière.
65. **Politiques vs mécanismes**
    `RetryPolicy` (quoi) + `Resilience4j` (comment).

## Concurrence

66. **Limiter l’état partagé**
    `Queue<Order>` thread-safe ou message bus.
67. **Protéger invariants**
    `synchronized` sur mutation de stock ou `AtomicInteger`.
68. **Gérer vie des threads**
    `ExecutorService` géré/fermé proprement (`shutdown`).
69. **Tester courses**
    Test concurrent d’`Inventory.reserve()` avec `CountDownLatch`.

## Smells fréquents

70. **Méthodes trop longues**
    Découper `checkout()` en `validate()`, `price()`, `pay()`, `ship()`.
71. **Duplications**
    Même calcul de frais de port → méthode unique.
72. **Train de paramètres**
    Remplacer `(street, zip, city, country)` par `Address`.
73. **Switch par type**
    Remplacer `if (role==ADMIN)...` par stratégies/polymorphisme.
74. **Commentaires expliquant du code confus**
    Refactorer pour se passer du commentaire.
75. **Etat global**
    Éviter `public static Config`. Injecter `Config`.
76. **Magic numbers/strings**
    `static final BigDecimal VAT_FR = new BigDecimal("0.20");`
77. **Chaining profond**
    `order.shipper().policy().rate()` → adapter `ShippingRateProvider`.
78. **Tests lents/fragiles**
    Éviter DB réelle → test avec H2/Testcontainers ciblés.
79. **Erreurs avalées**
    Pas de `catch(Exception e){}` silencieux.
80. **God class**
    Scinder `OrderManager` en services dédiés.

## Processus de refactoring (enchaînement)

81. **Tests de caractérisation**
    Capturer le comportement actuel d’un legacy avant de changer.
82. **Rendre testable**
    Introduire `PaymentGateway` interface pour pouvoir mocker.
83. **Supprimer duplications**
    Extraire `calculateVat()` utilisée partout.
84. **Renommer pour l’intention**
    `p()` → `payOutstandingBalance()`.
85. **Réduire taille**
    Déplacer `validateAddress()` de `OrderService` vers `AddressValidator`.
86. **Introduire abstractions locales**
    `DiscountPolicy` remplace plusieurs `if`.
87. **Simplifier flux**
    Early returns dans `checkout()`.
88. **Isoler frontières**
    `JdbcOrderRepository` derrière `OrderRepository`.
89. **Immuabilité**
    `OrderLines` immuables, renvoient nouvelle collection.
90. **Nettoyer tests**
    Data builder `CustomerBuilder.vip()` + `OrderBuilder.withLines(...)`.

