package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;

import java.util.List;

/**
 * Interface defining the contract for movie business operations.
 * <p>
 * This service interface specifies all business logic methods for managing movies.
 * Implementations are responsible for coordinating with the data layer and
 * ensuring business rules are applied to all operations.
 *
 * @author XPEHO
 * @see MovieServiceImpl
 */
public interface MovieService {

    /**
     * Retrieves all movies from the database.
     *
     * @return a list of all MovieEntity objects
     */
    List<MovieEntity> listMovies();

    /**
     * Creates a new movie in the database.
     *
     * @param request the movie data to create
     * @return the created MovieEntity with its generated ID
     */
    MovieEntity createMovie(CreateMovieRequest request);

    /**
     * Searches for movies by title (case-insensitive partial match).
     *
     * @param title the title text to search for
     * @return an Iterable of Movie objects matching the search criteria
     */
    Iterable<Movie> searchByTitle(String title);

    /**
     * Updates an existing movie with new data.
     *
     * @param id      the ID of the movie to update
     * @param request the new movie data
     * @return the updated MovieEntity
     */
    MovieEntity putMovie(Integer id, UpdateMovieRequest request);

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     */
    void deleteMovie(Integer id);

    /**
     * Retrieves a specific movie by its ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the MovieEntity with the specified ID
     */
    MovieEntity getMovieById(Integer id);

}
