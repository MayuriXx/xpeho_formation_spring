package com.xpeho.xpeho_formation_spring.presentation.handlers;

import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.usecases.*;
import com.xpeho.xpeho_formation_spring.presentation.controllers.MovieController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieHandler implements MovieController {

    private final GetAllMoviesUseCase getAllMoviesUseCase;
    private final CreateMovieUseCase createMovieUseCase;
    private final GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase;
    private final PutMovieUseCase putMovieUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;
    private final GetMovieByIdUseCase getMovieByIdUseCase;

    public MovieHandler(GetAllMoviesUseCase getAllMoviesUseCase, CreateMovieUseCase createMovieUseCase, GetAllMoviesByTitleUseCase getAllMoviesByTitleUseCase, PutMovieUseCase putMovieUseCase, DeleteMovieUseCase deleteMovieUseCase, GetMovieByIdUseCase getMovieByIdUseCase) {
        this.getAllMoviesUseCase = getAllMoviesUseCase;
        this.createMovieUseCase = createMovieUseCase;
        this.getAllMoviesByTitleUseCase = getAllMoviesByTitleUseCase;
        this.putMovieUseCase = putMovieUseCase;
        this.deleteMovieUseCase = deleteMovieUseCase;
        this.getMovieByIdUseCase = getMovieByIdUseCase;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return getAllMoviesUseCase.execute();
    }

    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        return createMovieUseCase.execute(request);
    }

    @Override
    public MovieEntity getMovieById(Integer id) {
        return getMovieByIdUseCase.execute(id);
    }

    @Override
    public List<MovieEntity> searchMoviesByTitle(String title) {
        return getAllMoviesByTitleUseCase.execute(title);
    }

    @Override
    public void deleteMovie(Integer id) {
        deleteMovieUseCase.execute(id);
    }

    @Override
    public MovieEntity putMovie(Integer id, UpdateMovieRequest request) {
        return putMovieUseCase.execute(id, request);
    }

}

