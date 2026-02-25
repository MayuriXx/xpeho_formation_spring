package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

/**
 * Use case for updating an existing movie.
 * <p>
 * This use case encapsulates the business logic for updating a movie in the system.
 * It validates the request and delegates the update operation to the service layer.
 *
 * @author XPEHO
 */
@Service
public class PutMovieUseCase {

    private final MovieService service;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for update operations
     */
    public PutMovieUseCase(MovieService service) {
        this.service = service;
    }

    /**
     * Executes the use case to update a movie.
     *
     * @param id      the unique identifier of the movie to update
     * @param request the new movie data (UpdateMovieRequest)
     * @return the updated MovieEntity
     */
    public MovieEntity execute(Integer id, UpdateMovieRequest request) {
        return service.putMovie(id, request);
    }
}
