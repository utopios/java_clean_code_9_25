# 1) Finalité des logs

* **Comprendre & diagnostiquer** sans debugger.
* **Auditer & tracer** qui a fait quoi et quand.
* **Observer** la santé (sans noyer les métriques).

# 2) Règles d’or

* **Loguer une seule fois** au bon endroit (aux “bords” : contrôleur, job, listener). Pas de “log & rethrow” en cascade.
* **Structurer** (JSON) plutôt que du texte libre.
* **Niveaux cohérents** :
  `TRACE` ultra-verbeux dev → `DEBUG` diagnostic → `INFO` faits métier notables → `WARN` anomalie gérable → `ERROR` incident.
* **Contexte obligatoire** : correlationId/traceId, userId métier, ressource, statut.
* **Zéro secret** : pas de mots de passe, tokens, numéros CB. Masquage systématique.
* **Courts & actionnables** : quoi, où, pourquoi (cause), impact.

# 3) Où loguer (architecture)

* **Edge/boundaries** (HTTP/gRPC/CLI/Batch) : début/fin, corrélation, résultat (succès/échec), durée.
* **Service métier** : événements métier importants (changement d’état), pas les détails I/O.
* **Infra/adapters** : erreurs techniques contextualisées (timeouts, DB down), jamais les payloads sensibles.


### Anti-patterns fréquents

* `System.out.println` (pas de niveau, pas de structure).
* Loguer des **objets géants** (DTO complets) ou **des boucles** élément par élément.
* Écrire des **logs dynamiques coûteux** (string concat) sans garde : utiliser `{}` (SLF4J) ou `isDebugEnabled()`.

### Mini-checklist “Clean Logs”

* [ ] JSON structuré, niveaux homogènes.
* [ ] CorrelationId propagé (MDC).
* [ ] Logs **au bord**, pas en cascade.
* [ ] Masquage PII/Secrets.
* [ ] Mesure des **durées** clés.
* [ ] Volume contrôlé (sampling/ratelimit).
* [ ] Séparation app/audit/sécurité.
* [ ] Tests de présence & de niveau.
