package com.xpeho.xpeho_formation_spring.presentation.controllers;

import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.presentation.handlers.MovieHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * REST API controller interface for movie operations.
 * <p>
 * This interface defines all REST endpoints for managing movies in the system.
 * It specifies the API contract including HTTP methods, request/response formats,
 * and Swagger/OpenAPI documentation for each endpoint.
 * <p>
 * All endpoints support cross-origin requests (CORS) and are prefixed with {@code /movies}.
 * The interface is implemented by {@link com.xpeho.xpeho_formation_spring.presentation.handlers.MovieHandler}.
 *
 * @author XPEHO
 * @see com.xpeho.xpeho_formation_spring.presentation.handlers.MovieHandler
 */
@CrossOrigin
@RequestMapping("/movies")
public interface MovieController {

    /**
     * Retrieves all movies.
     * GET /movies
     *
     * @return a list of all MovieEntity objects
     */
    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Get all movies",
            description = "Get all movies",
            tags = {"BDD"},
            operationId = "getAllMovies",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "List of movies"
                    )
            }
    )
    List<MovieEntity> getAllMovies();

    /**
     * Creates a new movie.
     * POST /movies
     *
     * @param request the movie data to create (request body)
     * @return the created MovieEntity with its generated ID
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Create a new movie",
            description = "Create a new movie",
            tags = {"BDD"},
            operationId = "createMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Movie created successfully"
                    )
            }
    )
    MovieEntity createMovie(@RequestBody CreateMovieRequest request);

    /**
     * Retrieves a movie by its ID.
     * GET /movies/{id}
     *
     * @param id the unique identifier of the movie to retrieve
     * @return the MovieEntity with the specified ID
     */
    @GetMapping(value = "{id}", produces = "application/json")
    @Operation(
            summary = "Get movie by id",
            description = "Get movie by id",
            tags = {"BDD"},
            operationId = "getMovieById",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Get movie by id"
                    )
            }
    )
    MovieEntity getMovieById(@PathVariable Integer id);

    /**
     * Searches for movies by title.
     * GET /movies/search?title=...
     *
     * @param title the title text to search for (partial, case-insensitive match)
     * @return a list of MovieEntity objects matching the search criteria
     */
    @GetMapping(value = "/search", produces = "application/json")
    @Operation(
            summary = "Search movies by title",
            description = "Search movies by title",
            tags = {"BDD"},
            operationId = "searchMoviesByTitle",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "List of movies matching the title"
                    )
            }
    )
    List<MovieEntity> searchMoviesByTitle(@RequestParam String title);

    /**
     * Deletes a movie by its ID.
     * DELETE /movies/{id}
     *
     * @param id the unique identifier of the movie to delete
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a movie",
            description = "Delete a movie by ID",
            tags = {"BDD"},
            operationId = "deleteMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Movie deleted successfully"
                    )
            }
    )
    void deleteMovie(@PathVariable Integer id);

    /**
     * Updates an existing movie.
     * PUT /movies/{id}
     *
     * @param id      the unique identifier of the movie to update
     * @param request the new movie data (request body)
     * @return the updated MovieEntity
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Update a new movie",
            description = "Update a new movie",
            tags = {"BDD"},
            operationId = "putMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Movie updated successfully"
                    )
            }
    )
    MovieEntity putMovie(@PathVariable Integer id, @RequestBody UpdateMovieRequest request);

    /**
     * Synchronizes movies from OMDb API into the local database.
     * POST /movies/sync?title=...&page=...
     * <p>
     * Calls the OMDb API with the given title and page number, saves new movies
     * into the database (duplicates are skipped), and returns the full list of
     * movies stored locally after synchronization.
     *
     * @param title the title keyword to search for in OMDb API
     * @param page  the page number to fetch (1-based, 10 results per page, default: 1)
     * @return the complete list of {@link MovieEntity} objects after synchronization
     * @throws IOException if the HTTP call to the OMDb API fails
     */
    @PostMapping(value = "/sync", produces = "application/json")
    @Operation(
            summary = "Sync movies from OMDb API",
            description = "Fetch movies from OMDb API by title and page, save new ones into the database and return the full list",
            tags = {"OMDB"},
            operationId = "syncMoviesWithOmdb",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Movies synchronized successfully"
                    )
            }
    )
    List<MovieEntity> syncMoviesWithOmdb(@RequestParam String title, @RequestParam(defaultValue = "1") int page) throws IOException;
}
