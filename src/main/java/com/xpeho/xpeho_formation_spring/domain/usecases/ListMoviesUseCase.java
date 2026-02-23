package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMoviesUseCase {

    private final MovieService service;

    public ListMoviesUseCase(MovieService service) {
        this.service = service;
    }

    public List<MovieEntity> execute() {
        return service.listMovies();
    }
}

