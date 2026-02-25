package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

/**
 * Use case for creating a new movie.
 * <p>
 * This use case encapsulates the business logic for creating a new movie in the system.
 * It validates the request and delegates the creation to the service layer.
 *
 * @author XPEHO
 */
@Service
public class CreateMovieUseCase {

    private final MovieService service;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for movie creation
     */
    public CreateMovieUseCase(MovieService service) {
        this.service = service;
    }

    /**
     * Executes the use case to create a new movie.
     *
     * @param request the movie data to create (CreateMovieRequest)
     * @return the created MovieEntity with its generated ID
     */
    public MovieEntity execute(CreateMovieRequest request) {
        return service.createMovie(request);
    }
}
