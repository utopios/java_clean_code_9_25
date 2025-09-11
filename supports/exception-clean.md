# 1) Principes de base

* **N’utilisons pas les exceptions pour le flux normal** (préférer validations, `Optional`, garde-fous).

```java
try {
  User u = repo.findById(id).get();
}catch(NoSuchElementException e) {
  ///
}

Optional<User> optionUser = repo.findById(id);
```


* **Fail fast** : détectons tôt, près de la source, avec un message clair.
* **Ne jamais avaler** une exception (`catch (Exception e) {}` vide) ; si on ne peut pas corriger localement, **propager**.
* **Loguons une seule fois** (au bord d’un boundary : contrôleur, listener, job). Pas de “log & rethrow” en cascade.
* **Message utile, sans secret** : contexte minimal (ids, états), pas de mots de passe, clés, requêtes SQL brutes, etc.
* **Checked vs unchecked** : en Java moderne, préférer **unchecked (RuntimeException)** pour les erreurs programmatiques/irréparables ; n’utiliser **checked** que si l’appelant peut raisonnablement réparer.

# 2) Hiérarchie d’exceptions “domaine”

Créons une racine claire, puis des sous-types significatifs.

```java
public class DomainException extends RuntimeException {
  public DomainException(String message) { super(message); }
  public DomainException(String message, Throwable cause) { super(message, cause); }
}

public class NotFoundException extends DomainException {
  public NotFoundException(String what, String id) {
    super(what + " not found: id=" + id);
  }
}

public class BusinessRuleViolation extends DomainException {
  public BusinessRuleViolation(String rule, String detail) {
    super("Rule violated: " + rule + " -> " + detail);
  }
}
```

Avantages : lisible, testable, facilement mappable en HTTP (404, 409, 422…), cohérent avec SOLID (Open/Closed).

# 3) Traduction par couches (Adapter/Boundary)

Chaque frontière traduit vers une exception de son niveau :

* **Infrastructure → Domaine** (ex. `SQLException` → `DomainException`)
* **Domaine → API** (ex. `BusinessRuleViolation` → HTTP 409/422)

```java
// Exemple repository
try {
  return jdbc.queryForObject(SQL, mapper, id);
} catch (EmptyResultDataAccessException e) {
  throw new NotFoundException("Customer", id);
} catch (DataAccessException e) {
  throw new DomainException("DB failure while loading customer " + id, e);
}
```

# 4) Gestion centralisée côté API (Spring)

Un seul endroit qui convertit en réponse HTTP propre.

```java
@RestControllerAdvice
class ApiExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(ApiError.of("NOT_FOUND", ex.getMessage()));
  }

  @ExceptionHandler(BusinessRuleViolation.class)
  ResponseEntity<ApiError> handleBusiness(BusinessRuleViolation ex) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
      .body(ApiError.of("BUSINESS_RULE", ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiError> handleUnknown(Exception ex) {
    // log une seule fois ici
    log.error("Unhandled error", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ApiError.of("INTERNAL_ERROR", "An unexpected error occurred."));
  }
}

record ApiError(String code, String message) {
  static ApiError of(String c, String m) { return new ApiError(c, m); }
}
```

# 5) Try-with-resources & garde-fous

* Utilisons `try (var r = ...) { ... }` pour fermer I/O automatiquement.
* Validons l’entrée **avant** d’appeler des services (préconditions claires).

```java
public Order placeOrder(OrderCmd cmd) {
  if (cmd.items().isEmpty()) {
    throw new BusinessRuleViolation("MIN_ITEMS", "At least one item is required");
  }
  // suite…
}
```

# 6) Transactions & idempotence

* En cas d’exception au milieu d’une écriture, **rollback** (Spring `@Transactional`).
* Pour des endpoints exposés, pensons **idempotence** (clé d’idempotence) pour éviter les effets de re-soumission après erreur.

```java
@PostMapping("/orders")
public ResponseEntity<?> createOrder(
    @RequestBody OrderCmd cmd,
    @RequestHeader("Idempotency-Key") String key) {
  return orderService.create(cmd, key);
}
```

# 7) Journalisation utile

* **Niveau** : `WARN` pour erreurs anticipées (ex. validation), `ERROR` pour incidents.
* **Corrélation** : ajoutez un **correlationId**/traceId au log et à la réponse.
* **Pas de double log** (pas de log en bas + rethrow + relog en haut).

# 8) Tests propres (TDD/BDD)

* Testons qu’une règle lève la bonne exception **et** le bon message.

```java
@Test
void placeOrder_requiresOneItem() {
  var cmd = new OrderCmd(List.of()); // vide
  var ex = assertThrows(BusinessRuleViolation.class, () -> service.placeOrder(cmd));
  assertTrue(ex.getMessage().contains("MIN_ITEMS"));
}
```

* Pour l’API, tests d’intégration : 422 sur règle métier, 404 sur non trouvé, 500 cachant le détail.

# 9) Alternatives fonctionnelles (quand pertinent)

* Pour des **erreurs attendues** au niveau applicatif, on peut retourner un type résultat (`Either<Result, Error>` / `Try`) au lieu d’exceptions, afin d’exprimer le **contrat** explicitement. En Java : Vavr `Either`, ou un petit record maison.

```java
sealed interface Result<T> permits Ok, Err {}
record Ok<T>(T value) implements Result<T> {}
record Err<T>(String code, String message) implements Result<T> {}

Result<OrderId> r = service.tryPlace(cmd);
if (r instanceof Ok<OrderId> ok) return ok.value();
// sinon mapper Err → HTTP
```

# 10) Anti-patterns à éviter

* Exceptions génériques (`throw new Exception("…")`), ou `catch (Throwable)`.
* Masquer la cause (`new X("…")` sans `cause`) → perte de stack.
* Renvoyer des codes d’erreur en plus des exceptions (doublons).
* Empiler des wrappings inutiles (pile d’exceptions peu lisible).
* Lancer des exceptions depuis des finalizers/close silencieux.

# 11) Checklist “clean”

* [ ] Une hiérarchie d’exceptions **courte et claire**.
* [ ] **Traduction** par couche + **gestion centralisée** au boundary.
* [ ] **Messages utiles**, sans données sensibles.
* [ ] **Logs uniques**, corrélés, niveaux adaptés.
* [ ] **Tests** pour les règles et le mapping HTTP.
* [ ] **Transactions** et **idempotence** pour les effets de bord.
* [ ] **try-with-resources** et validations “fail fast”.
