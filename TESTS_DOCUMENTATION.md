# Tests Documentation - XPEHO Formation Spring

## Overview

Le projet contient des tests unitaires pour chaque use case utilisant **JUnit 5** et **Mockito**.

Les tests sont organisés par use case et testent tous les scénarios possibles.

## Structure des tests

```
src/test/java/com/xpeho/xpeho_formation_spring/domain/usecases/
├── ListMoviesTest.java
├── CreateMovieTest.java
├── SearchMoviesTest.java
└── DeleteMovieTest.java
```

## 1. ListMoviesTest

**Fichier :** `src/test/java/com/xpeho/xpeho_formation_spring/domain/usecases/ListMoviesTest.java`

**Description :** Tests du use case `ListMovies` qui récupère tous les films.

### Tests inclus

#### Test 1: testExecute_ShouldReturnListOfMovies
- **Objectif :** Vérifier que la liste de films est retournée correctement
- **Données de test :** 2 films
- **Assertions :**
  - La liste n'est pas null
  - La liste contient 2 éléments
  - Les titres correspondent aux données attendues

#### Test 2: testExecute_ShouldReturnEmptyList
- **Objectif :** Vérifier le comportement avec une liste vide
- **Données de test :** Aucun film
- **Assertions :**
  - La liste n'est pas null
  - La liste est vide (size = 0)

### Lancer les tests

```bash
# Tous les tests
mvn test

# Seulement ListMoviesTest
mvn test -Dtest=ListMoviesTest

# Seulement un test spécifique
mvn test -Dtest=ListMoviesTest#testExecute_ShouldReturnListOfMovies
```

---

## 2. CreateMovieTest

**Fichier :** `src/test/java/com/xpeho/xpeho_formation_spring/domain/usecases/CreateMovieTest.java`

**Description :** Tests du use case `CreateMovie` qui crée un nouveau film.

### Tests inclus

#### Test 1: testExecute_ShouldCreateMovieSuccessfully
- **Objectif :** Vérifier la création réussie d'un film
- **Données de test :**
  - Titre : "New Movie"
  - Année : "2025"
  - IMDB ID : "tt1234567"
- **Assertions :**
  - Le film retourné n'est pas null
  - L'ID est correctement assigné (1)
  - Les propriétés correspondent aux données envoyées
  - La méthode service a été appelée

#### Test 2: testExecute_ShouldReturnMovieWithGeneratedId
- **Objectif :** Vérifier que l'ID est auto-généré (pas l'ID 1)
- **Données de test :**
  - Titre : "Spider-Man"
  - Année : "2002"
  - ID auto-généré : 10
- **Assertions :**
  - Le film n'est pas null
  - L'ID est 10 (auto-généré par la BD)
  - Le titre correspond

### Lancer les tests

```bash
mvn test -Dtest=CreateMovieTest
```

---

## 3. SearchMoviesTest

**Fichier :** `src/test/java/com/xpeho/xpeho_formation_spring/domain/usecases/SearchMoviesTest.java`

**Description :** Tests du use case `SearchMovies` qui cherche des films par titre.

### Tests inclus

#### Test 1: testExecute_ShouldSearchMoviesByTitle
- **Objectif :** Vérifier la recherche par titre
- **Données de test :** Recherche de "Spiderman"
  - 2 films trouvés
  - Années différentes (1990, 2010)
- **Assertions :**
  - La liste n'est pas null
  - La liste contient 2 éléments
  - Tous les films contiennent "Spiderman" dans le titre
  - La méthode service a été appelée avec le bon paramètre

#### Test 2: testExecute_ShouldReturnEmptyListWhenNoMoviesFound
- **Objectif :** Vérifier le comportement quand aucun film n'est trouvé
- **Données de test :** Recherche de "NonExistentMovie"
- **Assertions :**
  - La liste n'est pas null
  - La liste est vide (size = 0)
  - La méthode service a été appelée

### Lancer les tests

```bash
mvn test -Dtest=SearchMoviesTest
```

---

## 4. DeleteMovieTest

**Fichier :** `src/test/java/com/xpeho/xpeho_formation_spring/domain/usecases/DeleteMovieTest.java`

**Description :** Tests du use case `DeleteMovie` qui supprime un film.

### Tests inclus

#### Test 1: testExecute_ShouldDeleteMovieSuccessfully
- **Objectif :** Vérifier la suppression réussie
- **Données de test :** ID = 1
- **Assertions :**
  - La méthode service a été appelée avec le bon ID

#### Test 2: testExecute_ShouldDeleteMovieWithDifferentId
- **Objectif :** Vérifier la suppression avec un ID différent
- **Données de test :** ID = 10
- **Assertions :**
  - La méthode service a été appelée avec l'ID 10

#### Test 3: testExecute_ShouldCallServiceOnce
- **Objectif :** Vérifier que le service est appelé une seule fois
- **Données de test :** ID = 5
- **Assertions :**
  - La méthode service a été appelée exactement une fois

### Lancer les tests

```bash
mvn test -Dtest=DeleteMovieTest
```

---

## Exécution des tests

### Lancer tous les tests

```bash
mvn test
```

**Sortie attendue :**
```
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Lancer les tests avec rapport de couverture

```bash
mvn test jacoco:report
```

Le rapport de couverture sera généré dans : `target/site/jacoco/index.html`

### Lancer un test spécifique

```bash
mvn test -Dtest=ListMoviesTest
mvn test -Dtest=CreateMovieTest#testExecute_ShouldCreateMovieSuccessfully
```

### Lancer les tests avec Maven in debug mode

```bash
mvn test -Dmaven.surefire.debug
```

---

## Structure d'un test

Tous les tests suivent la même structure :

### 1. Setup (Préparation)

```java
@BeforeEach
void setUp() {
    // Initialisation du use case
    useCase = new UseCase(mockService);
}
```

### 2. Test avec le pattern Given-When-Then

```java
@Test
void testScenario() {
    // Given - Données de test
    Object input = new Object();
    when(mockService.method()).thenReturn(expectedResult);

    // When - Exécution
    Object result = useCase.execute(input);

    // Then - Vérifications
    assertNotNull(result);
    assertEquals(expectedValue, result.getProperty());
    verify(mockService).method();
}
```

### 3. Vérifications avec Mockito

```java
// Vérifier que la méthode a été appelée
verify(mockService).method();

// Vérifier le nombre d'appels
verify(mockService, times(1)).method();

// Vérifier que la méthode n'a pas été appelée
verify(mockService, never()).method();

// Vérifier les paramètres
verify(mockService).method(argumentCaptor.capture());
```

---

## Assertions courantes

### AssertJ (recommandé)

```java
// Vérifications de base
assertThat(result).isNotNull();
assertThat(result).isEqualTo(expected);
assertThat(result).isInstanceOf(MovieEntity.class);

// Collections
assertThat(list).hasSize(2);
assertThat(list).isEmpty();
assertThat(list).contains(element1, element2);

// Strings
assertThat(title).isEqualTo("Spiderman");
assertThat(title).containsIgnoringCase("spider");
```

### JUnit 5

```java
// Assertions simples
assertEquals(expected, actual);
assertNotNull(result);
assertTrue(condition);
assertFalse(condition);

// Assertions de collections
assertTrue(list.size() == 2);
assertArrayEquals(expected, actual);
```

---

## Dépannage des tests

### Erreur : "Cannot resolve symbol 'Mockito'"

**Solution :** Vérifier que Mockito est dans le pom.xml

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
```

### Erreur : "TooManyActualInvocations"

**Cause :** La méthode mockée a été appelée plus de fois que prévu

**Solution :** Vérifier le nombre d'appels dans `verify()`

```java
// Correct
verify(service, times(2)).method();

// Ou
verify(service).method();
verify(service).method();
```

### Erreur : "Wanted but not invoked"

**Cause :** La méthode mockée n'a pas été appelée

**Solution :**
1. Vérifier que le code appelant le service est correct
2. Vérifier que le mock est injecté correctement

---

## Bonnes pratiques

✅ **À FAIRE:**
- Un seul `@Test` par scénario de test
- Des noms de test explicites (`testExecute_ShouldReturnListOfMovies`)
- Utiliser Given-When-Then
- Mocker les dépendances externes
- Vérifier le comportement, pas l'implémentation

❌ **À ÉVITER:**
- Dépendances entre les tests
- Tests qui accèdent à la vraie base de données
- Noms de tests vagues (`test1`, `testMethod`)
- Trop d'assertions par test
- Tests trop complexes

---

## Rapport de test

Les résultats des tests sont stockés dans : `target/surefire-reports/`

Pour visualiser les résultats :

```bash
# Générer un rapport HTML
mvn surefire-report:report

# Ouvrir le rapport
open target/site/surefire-report.html
```

---

**Version :** 1.0  
**Dernière mise à jour :** 17 février 2026

