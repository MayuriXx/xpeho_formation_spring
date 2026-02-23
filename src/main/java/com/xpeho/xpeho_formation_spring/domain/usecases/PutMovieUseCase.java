package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class PutMovieUseCase {

    private final MovieService service;

    public PutMovieUseCase(MovieService service) {
        this.service = service;
    }

    public MovieEntity execute(Integer id, UpdateMovieRequest request) {
        return service.putMovie(id, request);
    }
}

