# Étude de cas – Benchmark de performances des Web Services REST

## Variantes à comparer
Trois implémentations REST doivent être créées :
<img width="329" height="217" alt="image" src="https://github.com/user-attachments/assets/237a125a-40ec-4768-90e2-8b81388d78a6" />
<img width="404" height="159" alt="image" src="https://github.com/user-attachments/assets/903a1e4d-7db5-42e9-94f9-a2b46b416915" />

## Environnement technique recommandé
<img width="407" height="226" alt="image" src="https://github.com/user-attachments/assets/5425632a-6ec6-4e1c-ba47-6797bea0d644" />

## Scénarios de test JMeter
### READ-heavy (relation incluse)
* 50% GET /items?page=&size=50
* 20% GET /items?categoryId=...&page=&size=
* 20% GET /categories/{id}/items?page=&size=
* 10% GET /categories?page=&size=
* Concurrence : 50 → 100 → 200 threads, ramp-up 60 s, 10 min/palier
### JOIN-filter ciblé
* 70% GET /items?categoryId=...&page=&size=
* 30% GET /items/{id}
* 60 → 120 threads, 8 min/palier, 60 s ramp-up
### MIXED (écritures sur deux entités)
* 40% GET /items?page=...
* 20% POST /items (1 KB)
* 10% PUT /items/{id} (1 KB)
* 10% DELETE /items/{id}
* 10% POST /categories (0.5–1 KB)
* 10% PUT /categories/{id}
* 50 → 100 threads, 10 min/palier
### HEAVY-body (payload 5 KB)
* 50% POST /items (5 KB)
* 50% PUT /items/{id} (5 KB)
* 30 → 60 threads, 8 min/palier
### Bonnes pratiques JMeter
* CSV Data Set Config pour ids existants (categories & items) et payloads variés.
* HTTP Request Defaults pour l’URL de la variante testée.
* Backend Listener → InfluxDB v2 (bucket jmeter, org perf).
* Listeners lourds désactivés pendant les runs.

## Auteur
* Nom : BEN-LAGHFIRI Majeda
* Cours: Architecture Microservices : Conception, Déploiement et Orchestration
* Date : Janvier 2026
* Encadré par : Pr.Mohamed LACHGAR
