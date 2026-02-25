package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Use case for retrieving all movies.
 * <p>
 * This use case encapsulates the business logic for fetching all movies from the system.
 * It orchestrates the call to the service layer and returns the complete movie list.
 *
 * @author XPEHO
 */
@Service
public class GetAllMoviesUseCase {

    private final MovieService service;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for data retrieval
     */
    public GetAllMoviesUseCase(MovieService service) {
        this.service = service;
    }

    /**
     * Executes the use case to retrieve all movies.
     *
     * @return a list of all MovieEntity objects available in the system
     */
    public List<MovieEntity> execute() {
        return service.listMovies();
    }
}
