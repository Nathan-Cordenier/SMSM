# SMSM — Backend

API REST du site web du Volley Club SMSM, construite avec **Spring Boot 3 / Java 21**.

---

## Stack technique

| Technologie | Rôle |
|---|---|
| Java 21 | Langage |
| Spring Boot 3.5 | Framework API REST |
| Spring Data JPA | Accès base de données |
| Hibernate | ORM (mapping objet-relationnel) |
| PostgreSQL 16 | Base de données |
| Lombok | Génération de code (getters, constructeurs...) |
| Swagger / OpenAPI | Documentation automatique de l'API |
| Docker + Docker Compose | Conteneurisation et orchestration |
| Maven | Gestion des dépendances |

---

## Prérequis

- Java 21+
- Docker Desktop
- IntelliJ IDEA (Ultimate recommandé)

---

## Lancer le projet en développement

### 1. Démarrer la base de données

Depuis la racine du repo `SMSM/` :

```bash
docker compose up -d
```

Vérifier que PostgreSQL tourne :

```bash
docker compose ps
```

### 2. Lancer Spring Boot

Depuis IntelliJ, lancer la classe `BackendApplication.java` via le bouton Run vert.

L'API est disponible sur : `http://localhost:8080`

### 3. Accéder à la documentation Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

## Commandes Docker utiles

```bash
# Démarrer tous les services (PostgreSQL)
docker compose up -d

# Arrêter tous les services (les données sont conservées)
docker compose down

# Voir l'état des conteneurs
docker compose ps

# Voir les logs de la base de données
docker compose logs bdd

# Supprimer le volume (⚠️ supprime toutes les données)
docker volume rm smsm_postgres_data

# Lister les volumes
docker volume ls
```

---

## Configuration

La configuration de l'application se trouve dans :

```
backend/src/main/resources/application.properties
```

| Propriété | Valeur | Description |
|---|---|---|
| `server.port` | `8080` | Port de l'API |
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/smsm_db` | URL de connexion PostgreSQL |
| `spring.jpa.hibernate.ddl-auto` | `update` | Hibernate crée/met à jour le schéma automatiquement |
| `spring.jpa.open-in-view` | `false` | Désactive la connexion BDD pendant le rendu |

---

## Architecture en couches

```
controller/      → Reçoit les requêtes HTTP, retourne du JSON
service/         → Logique métier
mapper/          → Conversion entre les types (Entity ↔ Domain ↔ DTO)
dao/             → Accès base de données (Spring Data JPA)
entity/          → Mapping des tables SQL (JPA)
domain/          → Objets métier purs
dto/             → Objets exposés à l'API
```

Règle absolue : **Controller → Service → DAO**. Jamais de saut de couche.

---

## Endpoints disponibles

Tous les endpoints sont documentés et testables via Swagger :
```
http://localhost:8080/swagger-ui/index.html
```

### Equipes

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/equipes` | Récupérer toutes les équipes |
| GET | `/api/equipes/{id}` | Récupérer une équipe par son id |
| POST | `/api/equipes` | Créer une équipe |
| PUT | `/api/equipes/{id}` | Modifier une équipe |
| DELETE | `/api/equipes/{id}` | Supprimer une équipe |

---

## Convention de commits

```
feat(scope): message      → nouvelle fonctionnalité
fix(scope): message       → correction de bug
chore(scope): message     → configuration, mise en place
refactor(scope): message  → refactoring sans nouvelle feature
```

Exemples :
```
feat(equipe): add POST endpoint
fix(auth): correct JWT expiration
chore(config): setup Swagger
```

---

## Workflow Git

```
main        → code stable, déployé en production (jamais de push direct)
develop     → branche d'intégration quotidienne
feature/xxx → nouvelle fonctionnalité (créée depuis develop, mergée via PR)
fix/xxx     → correction de bug
```