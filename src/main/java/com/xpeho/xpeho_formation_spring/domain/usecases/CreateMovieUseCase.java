package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class CreateMovieUseCase {

    private final MovieService service;

    public CreateMovieUseCase(MovieService service) {
        this.service = service;
    }

    public MovieEntity execute(CreateMovieRequest request) {
        return service.createMovie(request);
    }
}

