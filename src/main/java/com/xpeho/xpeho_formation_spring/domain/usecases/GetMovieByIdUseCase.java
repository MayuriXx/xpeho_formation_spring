package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

/**
 * Use case for retrieving a movie by its ID.
 *
 * This use case encapsulates the business logic for fetching a specific movie
 * from the system using its unique identifier.
 *
 * @author XPEHO
 */
@Service
public class GetMovieByIdUseCase {

    private final MovieService service;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for data retrieval
     */
    public GetMovieByIdUseCase(MovieService service) {
        this.service = service;
    }

    /**
     * Executes the use case to retrieve a movie by ID.
     *
     * @param id the unique identifier of the movie to retrieve
     * @return the MovieEntity with the specified ID
     */
    public MovieEntity execute(Integer id) {
        return service.getMovieById(id);
    }
}
