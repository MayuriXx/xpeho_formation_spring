# API Documentation - XPEHO Formation Spring

## Endpoints

### 1. List All Movies

**Endpoint :** `GET /movies`

**Description :** Récupère la liste complète de tous les films.

**Méthode HTTP :** GET

**Authentification :** Non requise

**Paramètres :** Aucun

**Réponse réussie (200 OK) :**
```json
[
  {
    "id": 1,
    "title": "Italian Spiderman",
    "year": "2007",
    "imdbId": "tt2705436",
    "type": "movie",
    "poster": "https://m.media-amazon.com/images/M/MV5BYWNiMmNlNmQtZTI2MS00MzAxLTgxM2QtNDY3ZGQxNDMwZDgzXkEyXkFqcGc@._V1_SX300.jpg"
  },
  {
    "id": 2,
    "title": "Superman, Spiderman or Batman",
    "year": "2011",
    "imdbId": "tt2084949",
    "type": "movie",
    "poster": "https://m.media-amazon.com/images/M/MV5BMjQ4MzcxNDU3N15BMl5BanBnXkFtZTgwOTE1MzMxNzE@._V1_SX300.jpg"
  }
]
```

**Exemple avec cURL :**
```bash
curl -X GET http://localhost:8080/movies \
  -H "Accept: application/json"
```

---

### 2. Create Movie

**Endpoint :** `POST /movies`

**Description :** Crée un nouveau film dans la base de données. L'ID est auto-généré.

**Méthode HTTP :** POST

**Content-Type :** application/json

**Authentification :** Non requise

**Corps de la requête :**
```json
{
  "title": "Iron Man",
  "year": "2008",
  "imdbId": "tt0371746",
  "type": "movie",
  "poster": "https://example.com/ironman.jpg"
}
```

**Réponse réussie (201 Created) :**
```json
{
  "id": 11,
  "title": "Iron Man",
  "year": "2008",
  "imdbId": "tt0371746",
  "type": "movie",
  "poster": "https://example.com/ironman.jpg"
}
```

**Exemple avec cURL :**
```bash
curl -X POST http://localhost:8080/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Iron Man",
    "year": "2008",
    "imdbId": "tt0371746",
    "type": "movie",
    "poster": "https://example.com/ironman.jpg"
  }'
```

**Exemple avec JavaScript (Fetch) :**
```javascript
const movie = {
  title: "Iron Man",
  year: "2008",
  imdbId: "tt0371746",
  type: "movie",
  poster: "https://example.com/ironman.jpg"
};

fetch('/movies', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(movie)
})
.then(response => response.json())
.then(data => console.log('Film créé:', data));
```

---

### 3. Search Movies by Title

**Endpoint :** `GET /movies/search`

**Description :** Cherche des films par titre (recherche partielle, insensible à la casse).

**Méthode HTTP :** GET

**Authentification :** Non requise

**Paramètres de requête :**
- `title` (string, obligatoire) - Le titre ou partie du titre à chercher

**Réponse réussie (200 OK) :**
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
  }
]
```

**Pas de résultat (200 OK avec liste vide) :**
```json
[]
```

**Exemple avec cURL :**
```bash
# Recherche case-insensitive
curl -X GET "http://localhost:8080/movies/search?title=spiderman" \
  -H "Accept: application/json"

# Avec un espace
curl -X GET "http://localhost:8080/movies/search?title=amazing%20spiderman"

# Recherche partielle
curl -X GET "http://localhost:8080/movies/search?title=spider"
```

**Exemple avec JavaScript (Fetch) :**
```javascript
const searchTitle = "spiderman";
const encodedTitle = encodeURIComponent(searchTitle);

fetch(`/movies/search?title=${encodedTitle}`)
  .then(response => response.json())
  .then(data => console.log('Résultats:', data));
```

---

### 4. Delete Movie

**Endpoint :** `DELETE /movies/{id}`

**Description :** Supprime un film par son ID.

**Méthode HTTP :** DELETE

**Authentification :** Non requise

**Paramètres de chemin :**
- `id` (integer, obligatoire) - L'ID du film à supprimer

**Réponse réussie (204 No Content) :** Aucun corps de réponse

**Erreur - Film non trouvé (404 Not Found) :**
```json
{
  "timestamp": "2026-02-17T12:00:00.000+0000",
  "status": 404,
  "error": "Not Found",
  "message": "Movie with id 999 not found"
}
```

**Exemple avec cURL :**
```bash
curl -X DELETE http://localhost:8080/movies/1

# Avec verbose pour voir les headers
curl -X DELETE http://localhost:8080/movies/1 -v
```

**Exemple avec JavaScript (Fetch) :**
```javascript
const movieId = 1;

fetch(`/movies/${movieId}`, {
  method: 'DELETE'
})
.then(response => {
  if (response.status === 204) {
    console.log('Film supprimé avec succès');
  }
});
```

---

## Codes de réponse HTTP

| Code | Description |
|------|-------------|
| 200 | OK - Requête réussie |
| 201 | Created - Ressource créée avec succès |
| 204 | No Content - Suppression réussie |
| 400 | Bad Request - Paramètres invalides |
| 404 | Not Found - Ressource non trouvée |
| 500 | Internal Server Error - Erreur serveur |

---

## Format des données

### Objet Movie

```json
{
  "id": 1,
  "title": "string (255 caractères max)",
  "year": "string (4 caractères)",
  "imdbId": "string (50 caractères max)",
  "type": "string (50 caractères max) - ex: 'movie', 'series'",
  "poster": "string (500 caractères max) - URL du poster"
}
```

---

## Librairies de test

Pour tester les APIs, vous pouvez utiliser :

### Postman

1. Télécharger [Postman](https://www.postman.com/downloads/)
2. Importer les endpoints manuellement ou utiliser les exemples cURL ci-dessus

### Swagger UI

Accéder à : `http://localhost:8080/swagger-ui.html`

Tous les endpoints sont documentés avec Swagger/OpenAPI 3.0

### cURL

Voir les exemples ci-dessus

### HTTPie

```bash
# Liste tous les films
http GET localhost:8080/movies

# Créer un film
http POST localhost:8080/movies \
  title="Iron Man" \
  year="2008" \
  imdbId="tt0371746" \
  type="movie" \
  poster="https://example.com/ironman.jpg"

# Chercher par titre
http GET localhost:8080/movies/search title==spiderman

# Supprimer un film
http DELETE localhost:8080/movies/1
```

---

## Cas d'usage courants

### Récupérer et afficher tous les films

```bash
curl -X GET http://localhost:8080/movies | jq .
```

### Ajouter 5 films différents

```bash
for i in {1..5}; do
  curl -X POST http://localhost:8080/movies \
    -H "Content-Type: application/json" \
    -d "{\"title\":\"Movie $i\",\"year\":\"202$i\",\"imdbId\":\"tt$i\",\"type\":\"movie\",\"poster\":\"https://example.com/$i.jpg\"}"
done
```

### Chercher tous les films contenant "Spider"

```bash
curl -X GET "http://localhost:8080/movies/search?title=Spider"
```

### Supprimer le film avec l'ID 5

```bash
curl -X DELETE http://localhost:8080/movies/5
```

---

**Version API :** 1.0  
**Dernière mise à jour :** 17 février 2026

