package com.xpeho.xpeho_formation_spring.presentation.handlers;

import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.usecases.*;
import com.xpeho.xpeho_formation_spring.presentation.controllers.MovieController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handler implementing the MovieController interface.
 *
 * This class acts as the presentation layer handler that receives HTTP requests,
 * delegates them to the appropriate use cases, and returns the responses.
 * It implements the MovieController interface and routes all requests to the
 * corresponding domain use cases.
 *
 * @author XPEHO
 */
@RestController
public class MovieHandler implements MovieController {

    private final GetAllMoviesUseCase getAllMoviesUseCase;
    private final CreateMovieUseCase createMovieUseCase;
    private final GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase;
    private final PutMovieUseCase putMovieUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    /**
     * Constructor with dependency injection for all use cases.
     *
     * @param getAllMoviesUseCase use case for retrieving all movies
     * @param createMovieUseCase use case for creating movies
     * @param getAllMoviesByTitleUseCase use case for searching movies by title
     * @param putMovieUseCase use case for updating movies
     * @param deleteMovieUseCase use case for deleting movies
     * @param getMovieByIdUseCase use case for retrieving a movie by ID
     */
    public MovieHandler(GetAllMoviesUseCase getAllMoviesUseCase, CreateMovieUseCase createMovieUseCase, GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase, PutMovieUseCase putMovieUseCase, DeleteMovieUseCase deleteMovieUseCase, GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getAllMoviesUseCase = getAllMoviesUseCase;
        this.createMovieUseCase = createMovieUseCase;
        this.getAllMoviesByTitleUseCase = getAllMoviesByTitleUseCase;
        this.putMovieUseCase = putMovieUseCase;
        this.deleteMovieUseCase = deleteMovieUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    /**
     * Handles GET /movies request.
     * Delegates to GetAllMoviesUseCase.
     *
     * @return a list of all MovieEntity objects
     */
    @Override
    public List<MovieEntity> getAllMovies() {
        return getAllMoviesUseCase.execute();
    }

    /**
     * Handles POST /movies request.
     * Delegates to CreateMovieUseCase.
     *
     * @param request the movie data to create
     * @return the created MovieEntity
     */
    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        return createMovieUseCase.execute(request);
    }

    /**
     * Handles GET /movies/{id} request.
     * Delegates to GetMovieByIdUseCase.
     *
     * @param id the unique identifier of the movie
     * @return the MovieEntity with the specified ID
     */
    @Override
    public MovieEntity getMovieById(Integer id) {
        return getMovieByIdUseCase.execute(id);
    }

    /**
     * Handles GET /movies/search?title=... request.
     * Delegates to GetAllMoviesByTitleUseCase.
     *
     * @param title the title text to search for
     * @return a list of MovieEntity objects matching the search criteria
     */
    @Override
    public List<MovieEntity> searchMoviesByTitle(String title) {
        return getAllMoviesByTitleUseCase.execute(title);
    }

    /**
     * Handles DELETE /movies/{id} request.
     * Delegates to DeleteMovieUseCase.
     *
     * @param id the unique identifier of the movie to delete
     */
    @Override
    public void deleteMovie(Integer id) {
        deleteMovieUseCase.execute(id);
    }

    /**
     * Handles PUT /movies/{id} request.
     * Delegates to PutMovieUseCase.
     *
     * @param id the unique identifier of the movie to update
     * @param request the new movie data
     * @return the updated MovieEntity
     */
    @Override
    public MovieEntity putMovie(Integer id, UpdateMovieRequest request) {
        return putMovieUseCase.execute(id, request);
    }

}
