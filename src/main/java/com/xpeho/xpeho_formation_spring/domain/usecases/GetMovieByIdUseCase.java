package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class GetMovieByIdUseCase {

    private final MovieService service;

    public GetMovieByIdUseCase(MovieService service) {
        this.service = service;
    }

    public MovieEntity execute(Integer id) {
        return service.getMovieById(id);
    }
}

