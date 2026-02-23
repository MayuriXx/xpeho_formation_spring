package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class DeleteMovieUseCase {

    private final MovieService service;

    public DeleteMovieUseCase(MovieService service) {
        this.service = service;
    }

    public void execute(Integer id) {
        service.deleteMovie(id);
    }
}

