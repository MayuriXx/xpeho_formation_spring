package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

/**
 * Use case for deleting a movie.
 *
 * This use case encapsulates the business logic for removing a movie from the system.
 * It delegates the deletion operation to the service layer.
 *
 * @author XPEHO
 */
@Service
public class DeleteMovieUseCase {

    private final MovieService service;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for deletion operations
     */
    public DeleteMovieUseCase(MovieService service) {
        this.service = service;
    }

    /**
     * Executes the use case to delete a movie.
     *
     * @param id the unique identifier of the movie to delete
     */
    public void execute(Integer id) {
        service.deleteMovie(id);
    }
}
