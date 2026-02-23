package com.xpeho.xpeho_formation_spring.data.services;

import com.xpeho.xpeho_formation_spring.data.converters.MovieConverter;
import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.data.sources.MovieRepository;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Implementation of the MovieService.
 *
 * This class manages all CRUD operations on movies.
 * It communicates with the database via MovieRepository
 * and performs conversions between models and entities.
 *
 * @author XPEHO
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final MovieConverter converter;

    /**
     * Constructor with dependency injection.
     *
     * @param repository the repository for accessing movie data
     * @param converter the converter for transforming models
     */
    public MovieServiceImpl(MovieRepository repository, MovieConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    /**
     * Retrieves the list of all movies.
     *
     * @return a list of MovieEntity containing all movies from the database
     */
    @Override
    public List<MovieEntity> listMovies() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(converter::modelToEntity)
                .toList();
    }

    /**
     * Creates a new movie and saves it to the database.
     *
     * @param request the data for the new movie (CreateMovieRequest)
     * @return the entity of the created movie with its generated ID
     */
    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        var newMovie = new Movie(0, request.title(), request.year(), request.imdbId(), request.type(), request.poster());
        var savedMovie = repository.save(newMovie);
        return converter.modelToEntity(savedMovie);
    }

    /**
     * Searches for movies whose title contains the provided text (case-insensitive).
     *
     * @param title the title to search for (partial match)
     * @return an Iterable containing all matching movies
     */
    @Override
    public Iterable<Movie> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     */
    @Override
    public void deleteMovie(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the entity of the found movie
     * @throws RuntimeException if the movie does not exist
     */
    @Override
    public MovieEntity getMovieById(Integer id) {
        return repository.findById(id)
                .map(converter::modelToEntity)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    /**
     * Updates an existing movie with new data.
     *
     * @param id the ID of the movie to update
     * @param request the new movie data (UpdateMovieRequest)
     * @return the entity of the updated movie
     * @throws RuntimeException if the movie does not exist
     */
    @Override
    public MovieEntity putMovie(Integer id, UpdateMovieRequest request) {
        var existingMovie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        var updatedMovie = new Movie(
                existingMovie.id(),
                request.title(),
                request.year(),
                request.imdbId(),
                request.type(),
                request.poster()
        );

        var savedMovie = repository.save(updatedMovie);
        return converter.modelToEntity(savedMovie);
    }

}
