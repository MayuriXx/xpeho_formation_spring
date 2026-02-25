package com.xpeho.xpeho_formation_spring.data.sources;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Movie data access.
 * <p>
 * This interface provides CRUD operations and custom queries for Movie entities.
 * It extends CrudRepository to inherit standard data access operations,
 * and defines custom finder methods for specific business requirements.
 *
 * @author XPEHO
 */
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    /**
     * Finds all movies whose title contains the given text (case-insensitive).
     *
     * @param title the text to search for in movie titles
     * @return an Iterable of movies matching the search criteria
     */
    Iterable<Movie> findByTitleContainingIgnoreCase(String title);
}
