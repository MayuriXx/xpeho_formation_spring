# XPEHO Formation Spring - Documentation


XPEHO Formation Spring est une application REST API construite avec **Spring Boot 4.0.2** et **Java 25**, permettant de gérer une collection de films avec les opérations CRUD complètes.

## 🏗️ Architecture

Le projet suit une architecture en couches clean :

```
presentation/
  ├── controllers/     # Interfaces des endpoints REST
  └── handlers/        # Implémentations des controllers

domain/
  ├── entities/        # Entités métier (MovieEntity)
  ├── services/        # Logique métier (MovieService)
  └── usecases/        # Cas d'usage (ListMovies, CreateMovie, etc.)

data/
  ├── models/          # Modèles de données JDBC (Movie)
  ├── sources/         # Repositories (MovieRepository)
  └── converters/      # Convertisseurs Model <-> Entity
```

## 🎯 Endpoints disponibles

### 1️⃣ Récupérer tous les films
```bash
GET /movies
```

**Réponse (200 OK) :**
```json
[
  {
    "id": 1,
    "title": "Italian Spiderman",
    "year": "2007",
    "imdbId": "tt2705436",
    "type": "movie",
    "poster": "https://m.media-amazon.com/images/..."
  },
  ...
]
```

### 2️⃣ Ajouter un nouveau film
```bash
POST /movies
Content-Type: application/json

{
  "title": "Iron Man",
  "year": "2008",
  "imdbId": "tt0371746",
  "type": "movie",
  "poster": "https://example.com/poster.jpg"
}
```

**Réponse (201 Created) :**
```json
{
  "id": 11,
  "title": "Iron Man",
  "year": "2008",
  "imdbId": "tt0371746",
  "type": "movie",
  "poster": "https://example.com/poster.jpg"
}
```

**Note :** L'`id` est **auto-généré** par la base de données, ne pas le renseigner.

### 3️⃣ Chercher un film par titre
```bash
GET /movies/search?title=spiderman
```

**Réponse (200 OK) :**
```json
[
  {
    "id": 1,
    "title": "Italian Spiderman",
    "year": "2007",
    "imdbId": "tt2705436",
    "type": "movie",
    "poster": "https://..."
  },
  {
    "id": 3,
    "title": "Spiderman",
    "year": "1990",
    "imdbId": "tt0100669",
    "type": "movie",
    "poster": "N/A"
  },
  ...
]
```

**Note :** La recherche est **insensible à la casse** (case-insensitive).

### 4️⃣ Supprimer un film
```bash
DELETE /movies/1
```

**Réponse (204 No Content) :** Aucun corps de réponse

## 📦 Dépendances principales

- **Spring Boot 4.0.2** - Framework web
- **Spring Data JDBC 4.0.2** - Accès à la base de données
- **H2 Database 2.4.240** - Base de données embarquée
- **SpringDoc OpenAPI 3.0.1** - Swagger/OpenAPI 3.0
- **JUnit 5** - Framework de test
- **Mockito** - Framework de mock pour les tests
- **Java 25** - Langage de programmation

## 🗄️ Structure de la base de données

### Table MOVIE

```sql
CREATE TABLE MOVIE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    "year" VARCHAR(4),
    "imdb_id" VARCHAR(50),
    "type" VARCHAR(50),
    poster VARCHAR(500)
);
```

**Colonnes :**
- `id` : Identifiant unique (auto-incrémenté)
- `title` : Titre du film
- `year` : Année de sortie
- `imdb_id` : Identifiant IMDB
- `type` : Type de contenu (movie, series, etc.)
- `poster` : URL du poster

## 🧪 Tests unitaires

Le projet inclut des tests pour chaque use case utilisant **JUnit 5** et **Mockito**.

### Lancer les tests

```bash
# Tous les tests
mvn test

# Tests spécifiques
mvn test -Dtest=ListMoviesTest
mvn test -Dtest=CreateMovieTest
mvn test -Dtest=SearchMoviesTest
mvn test -Dtest=DeleteMovieTest
```

### Couverture des tests

| Use Case | Tests | Couverture |
|----------|-------|-----------|
| ListMovies | 2 tests | ✅ Liste avec films, liste vide |
| CreateMovie | 2 tests | ✅ Création réussie, ID auto-généré |
| SearchMovies | 2 tests | ✅ Recherche trouvée, pas de résultats |
| DeleteMovie | 2 tests | ✅ Suppression réussie |

## 🔄 Use Cases

### 1. ListMovies
Récupère tous les films de la base de données.

**Dépendances :**
- `MovieService` - Pour accéder aux films

### 2. CreateMovie
Crée un nouveau film sans renseigner l'ID.

**Dépendances :**
- `MovieService` - Pour sauvegarder le film

**Entrée :** `CreateMovieRequest` (sans id)
**Sortie :** `MovieEntity` (avec id auto-généré)

### 3. SearchMovies
Cherche des films par titre (case-insensitive).

**Dépendances :**
- `MovieService` - Pour chercher les films
- `MovieConverter` - Pour convertir les modèles

**Entrée :** String (titre du film)
**Sortie :** List<MovieEntity>

### 4. DeleteMovie
Supprime un film par son ID.

**Dépendances :**
- `MovieService` - Pour supprimer le film

**Entrée :** Integer (id du film)

## 🔌 Services

### MovieService

Interface définissant les opérations sur les films.

```java
public interface MovieService {
    List<MovieEntity> listMovies();
    MovieEntity createMovie(CreateMovieRequest request);
    Iterable<Movie> searchByTitle(String title);
    void deleteMovie(Integer id);
}
```

### MovieServiceImpl

Implémentation du service avec accès à la base de données via `MovieRepository`.

## 🔄 Convertisseurs

### MovieConverter

Convertit entre les modèles `Movie` (JDBC) et `MovieEntity` (domaine).

```java
public MovieEntity modelToEntity(Movie model)      // Movie -> MovieEntity
public Movie entityToModel(MovieEntity entity)     // MovieEntity -> Movie
```

## 📝 DTOs

### CreateMovieRequest

DTO pour la création d'un film (sans id).

```java
public record CreateMovieRequest(
    String title,
    String year,
    String imdbId,
    String type,
    String poster
)
```

### MovieEntity

Entité du domaine représentant un film.

```java
public record MovieEntity(
    int id,
    String title,
    String year,
    String imdbId,
    String type,
    String poster
)
```

## 🚀 Démarrage de l'application

### Prérequis
- Java 25 installé
- Maven 3.9.12 ou supérieur

### Variables d'environnement

Copier le fichier template et renseigner les valeurs :

```bash
cp .env.template .env
```

| Variable       | Description              | Obligatoire |
|----------------|--------------------------|-------------|
| `OMDB_API_KEY` | Clé API OMDb ([obtenir une clé](https://www.omdbapi.com/apikey.aspx)) | ✅ |

### Commandes

```bash
# Compiler et construire
mvn clean install

# Démarrer l'application
mvn spring-boot:run

# Ou depuis le fichier JAR
java -jar target/xpeho_formation_spring-0.0.1-SNAPSHOT.jar
```

L'application démarre sur **http://localhost:8080**

## 📚 Accès à la documentation Swagger

Une fois l'application démarrée, accédez à :

```
http://localhost:8080/swagger-ui.html
```

Vous pouvez tester les endpoints directement depuis l'interface Swagger.

## 💾 Initialisation des données

Les données initiales (10 films) sont chargées automatiquement au démarrage via le fichier `data.sql` :

```sql
src/main/resources/data.sql
```

## ⚙️ Configuration

### Application Properties

```properties
src/main/resources/application.properties
```

Configuration de la base de données H2 embarquée.

## 🔐 Notes de sécurité

- Les noms de colonnes réservés (`year`, `type`, `imdb_id`) sont échappés avec des guillemets
- La recherche utilise des requêtes paramétrées pour éviter les injections SQL
- Les endpoints acceptent uniquement du JSON

## 📊 Schéma de l'application

```
Request HTTP
     ↓
MovieController (interface)
     ↓
MovieHandler (implémentation)
     ↓
Use Case (ListMovies, CreateMovie, SearchMovies, DeleteMovie)
     ↓
MovieService (logique métier)
     ↓
MovieRepository (accès données)
     ↓
H2 Database
```

## 🐛 Dépannage

### Erreur "Table MOVIE not found"
Vérifier que le fichier `schema.sql` existe et contient la création de la table MOVIE.

### Erreur "Column not found"
Les noms de colonnes réservés SQL doivent être échappés avec des guillemets dans les annotations `@Column`.

### Tests qui échouent
S'assurer que Mockito est bien configuré et que les mocks sont correctement initialisés avec `@ExtendWith(MockitoExtension.class)`.

## 📞 Support

Pour toute question ou problème, consultez la documentation officielle de :
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [SpringDoc OpenAPI](https://springdoc.org/)

---

**Version :** 0.0.1-SNAPSHOT  
**Dernière mise à jour :** 17 février 2026  
**Auteur :** XPEHO Formation

