package com.xpeho.xpeho_formation_spring.presentation.controllers;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movies")
public interface MovieController {

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Get all movies",
            description = "Get all movies",
            tags = {"movies"},
            operationId = "getAllMovies",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "List of movies"
                    )
            }
    )
    List<MovieEntity> getAllMovies();

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Create a new movie",
            description = "Create a new movie",
            tags = {"movies"},
            operationId = "createMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Movie created successfully"
                    )
            }
    )
    MovieEntity createMovie(@RequestBody CreateMovieRequest request);

    @GetMapping(value = "/search", produces = "application/json")
    @Operation(
            summary = "Search movies by title",
            description = "Search movies by title",
            tags = {"movies"},
            operationId = "searchMoviesByTitle",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "List of movies matching the title"
                    )
            }
    )
    List<MovieEntity> searchMoviesByTitle(@RequestParam String title);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a movie",
            description = "Delete a movie by ID",
            tags = {"movies"},
            operationId = "deleteMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Movie deleted successfully"
                    )
            }
    )
    void deleteMovie(@PathVariable Integer id);

    @PutMapping(path = "/{id}",consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Update a new movie",
            description = "Update a new movie",
            tags = {"movies"},
            operationId = "putMovie",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Movie updated successfully"
                    )
            }
    )
    void putMovie(@PathVariable Integer id);
}

