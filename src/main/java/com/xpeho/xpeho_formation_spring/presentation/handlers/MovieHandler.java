package com.xpeho.xpeho_formation_spring.presentation.handlers;

import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.usecases.*;
import com.xpeho.xpeho_formation_spring.presentation.controllers.MovieController;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Handler implementing the MovieController interface.
 * <p>
 * This class acts as the presentation layer handler that receives HTTP requests,
 * delegates them to the appropriate use cases, and returns the responses.
 * <p>
 * It implements the {@link MovieController} interface and routes each request
 * to its corresponding domain use case.
 *
 * @author XPEHO
 * @see MovieController
 */
@RestController
public class MovieHandler implements MovieController {

    private final GetAllMoviesUseCase getAllMoviesUseCase;
    private final CreateMovieUseCase createMovieUseCase;
    private final GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase;
    private final PutMovieUseCase putMovieUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;
    private final GetMovieByIdUseCase getMovieByIdUseCase;
    private final SyncMoviesWithOmdbUseCase syncMoviesWithOmdbUseCase;

    /**
     * Constructor with dependency injection for all use cases.
     *
     * @param getAllMoviesUseCase        use case for retrieving all movies
     * @param createMovieUseCase         use case for creating movies
     * @param getAllMoviesByTitleUseCase use case for searching movies by title
     * @param putMovieUseCase            use case for updating movies
     * @param deleteMovieUseCase         use case for deleting movies
     * @param getMovieByIdUseCase        use case for retrieving a movie by ID
     * @param syncMoviesWithOmdbUseCase  use case for synchronizing movies with OMDb API
     */
    public MovieHandler(GetAllMoviesUseCase getAllMoviesUseCase, CreateMovieUseCase createMovieUseCase, GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase, PutMovieUseCase putMovieUseCase, DeleteMovieUseCase deleteMovieUseCase, GetMovieByIdUseCase getMovieByIdUseCase, SyncMoviesWithOmdbUseCase syncMoviesWithOmdbUseCase) {
        this.getAllMoviesUseCase = getAllMoviesUseCase;
        this.createMovieUseCase = createMovieUseCase;
        this.getAllMoviesByTitleUseCase = getAllMoviesByTitleUseCase;
        this.putMovieUseCase = putMovieUseCase;
        this.deleteMovieUseCase = deleteMovieUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
        this.syncMoviesWithOmdbUseCase = syncMoviesWithOmdbUseCase;
    }

    /**
     * Handles GET /movies request.
     * Returns all movies stored in the database.
     *
     * @return a list of all {@link MovieEntity} objects
     */
    @Override
    public List<MovieEntity> getAllMovies() {
        return getAllMoviesUseCase.execute();
    }

    /**
     * Handles POST /movies request.
     * Creates a new movie in the database.
     *
     * @param request the movie data to create
     * @return the created {@link MovieEntity} with its generated ID
     */
    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        return createMovieUseCase.execute(request);
    }

    /**
     * Handles GET /movies/{id} request.
     * Returns the movie matching the given ID.
     *
     * @param id the unique identifier of the movie
     * @return the {@link MovieEntity} with the specified ID
     */
    @Override
    public MovieEntity getMovieById(Integer id) {
        return getMovieByIdUseCase.execute(id);
    }

    /**
     * Handles GET /movies/search?title=... request.
     * <p>
     * First synchronizes page 1 of OMDb API results for the given title to enrich
     * the local database, then searches and returns matching movies locally.
     * If the OMDb sync fails, the local search still proceeds normally.
     *
     * @param title the title text to search for (partial, case-insensitive match)
     * @return a list of {@link MovieEntity} objects matching the search criteria
     */
    @Override
    public List<MovieEntity> searchMoviesByTitle(String title) {
        try {
            // First sync with OMDb using the search title
            syncMoviesWithOmdbUseCase.execute(title, 1);
        } catch (Exception e) {
            // Continue with local search even if OMDb sync fails
        }
        // Then search in local database
        return getAllMoviesByTitleUseCase.execute(title);
    }

    /**
     * Handles DELETE /movies/{id} request.
     * Deletes the movie matching the given ID from the database.
     *
     * @param id the unique identifier of the movie to delete
     */
    @Override
    public void deleteMovie(Integer id) {
        deleteMovieUseCase.execute(id);
    }

    /**
     * Handles PUT /movies/{id} request.
     * Updates the movie matching the given ID with the provided data.
     *
     * @param id      the unique identifier of the movie to update
     * @param request the new movie data
     * @return the updated {@link MovieEntity}
     */
    @Override
    public MovieEntity putMovie(Integer id, UpdateMovieRequest request) {
        return putMovieUseCase.execute(id, request);
    }

    /**
     * Handles POST /movies/sync?title=...&page=... request.
     * <p>
     * Delegates to {@link SyncMoviesWithOmdbUseCase} which fetches movies from
     * the OMDb API for the given title and page, saves new ones into the database,
     * and returns the full list of movies stored locally.
     *
     * @param title the title keyword to search for in OMDb API
     * @param page  the page number to fetch (1-based, 10 results per page)
     * @return the complete list of {@link MovieEntity} objects after synchronization
     * @throws IOException if the HTTP call to the OMDb API fails
     */
    @Override
    public List<MovieEntity> syncMoviesWithOmdb(String title, int page) throws IOException {
        return syncMoviesWithOmdbUseCase.execute(title, page);
    }

}
