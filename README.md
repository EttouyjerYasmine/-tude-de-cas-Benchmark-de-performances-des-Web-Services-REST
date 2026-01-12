# Étude de cas – Benchmark de performances des Web Services REST

## Variantes à comparer
Trois implémentations REST doivent être créées :
<img width="329" height="217" alt="image" src="https://github.com/user-attachments/assets/237a125a-40ec-4768-90e2-8b81388d78a6" />
<img width="404" height="159" alt="image" src="https://github.com/user-attachments/assets/903a1e4d-7db5-42e9-94f9-a2b46b416915" />

## Environnement technique recommandé
<img width="407" height="226" alt="image" src="https://github.com/user-attachments/assets/5425632a-6ec6-4e1c-ba47-6797bea0d644" />

## Plan de Tests JMeter

### 1. Scénario READ-heavy (avec relations)
**Objectif** : Évaluer les performances en lecture intensive avec jointures.

**Répartition des requêtes** :
- 50% – Liste paginée des items : `GET /items?page={page}&size=50`
- 20% – Filtrage par catégorie : `GET /items?categoryId={id}&page={page}&size={size}`
- 20% – Items par catégorie : `GET /categories/{id}/items?page={page}&size={size}`
- 10% – Liste des catégories : `GET /categories?page={page}&size={size}`

**Profil de charge** :
- Utilisateurs simultanés : 50 → 100 → 200
- Durée : 10 minutes par palier
- Montée en charge : 60 secondes

---

### 2. Scénario JOIN-filter ciblé
**Objectif** : Tester les requêtes avec jointures et filtres spécifiques.

**Répartition des requêtes** :
- 70% – Filtrage par catégorie : `GET /items?categoryId={id}&page={page}&size={size}`
- 30% – Consultation d’un item : `GET /items/{id}`

**Profil de charge** :
- Utilisateurs simultanés : 60 → 120
- Durée : 8 minutes par palier
- Montée en charge : 60 secondes

---

### 3. Scénario MIXED (lectures et écritures)
**Objectif** : Simuler une charge mixte sur les entités Items et Categories.

**Répartition des requêtes** :
- 40% – Liste paginée des items : `GET /items?page={page}&size={size}`
- 20% – Création d’item : `POST /items` (payload ~1 Ko)
- 10% – Mise à jour d’item : `PUT /items/{id}` (payload ~1 Ko)
- 10% – Suppression d’item : `DELETE /items/{id}`
- 10% – Création de catégorie : `POST /categories` (payload 0,5–1 Ko)
- 10% – Mise à jour de catégorie : `PUT /categories/{id}`

**Profil de charge** :
- Utilisateurs simultanés : 50 → 100
- Durée : 10 minutes par palier
- Montée en charge : progressive

---

### 4. Scénario HEAVY-body (payload volumineux)
**Objectif** : Évaluer le traitement des requêtes avec corps de requête lourds.

**Répartition des requêtes** :
- 50% – Création d’item : `POST /items` (payload 5 Ko)
- 50% – Mise à jour d’item : `PUT /items/{id}` (payload 5 Ko)

**Profil de charge** :
- Utilisateurs simultanés : 30 → 60
- Durée : 8 minutes par palier
- Montée en charge : progressive

---

### 5. Configuration JMeter et bonnes pratiques

**Préparation des données** :
- Utilisation de *CSV Data Set Config* pour les identifiants existants (catégories et items) et les payloads variés.

**Configuration commune** :
- *HTTP Request Defaults* pour définir l’URL de l’environnement testé.

**Monitoring et export** :
- *Backend Listener* configuré vers InfluxDB v2 (bucket : `jmeter`, organisation : `perf`) pour la collecte des métriques.
- Désactivation des listeners graphiques pendant l’exécution pour réduire l’impact sur les performances.

---

**Auteur** : Ettouyjer yasmine
**Contexte** : Cours *Architecture Microservices – Conception, Déploiement et Orchestration*  
**Date** : Janvier 2026  
**Encadrement** : Pr. Mohamed LACHGAR
