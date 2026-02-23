package com.xpeho.xpeho_formation_spring.presentation.handlers;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.usecases.CreateMovieUseCase;
import com.xpeho.xpeho_formation_spring.domain.usecases.DeleteMovieUseCase;
import com.xpeho.xpeho_formation_spring.domain.usecases.ListMoviesUseCase;
import com.xpeho.xpeho_formation_spring.domain.usecases.SearchMoviesUseCase;
import com.xpeho.xpeho_formation_spring.presentation.controllers.MovieController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieHandler implements MovieController {

    private final ListMoviesUseCase listMoviesUseCase;
    private final CreateMovieUseCase createMovieUseCase;
    private final SearchMoviesUseCase searchMoviesUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;

    public MovieHandler(ListMoviesUseCase listMoviesUseCase, CreateMovieUseCase createMovieUseCase, SearchMoviesUseCase searchMoviesUseCase, DeleteMovieUseCase deleteMovieUseCase) {
        this.listMoviesUseCase = listMoviesUseCase;
        this.createMovieUseCase = createMovieUseCase;
        this.searchMoviesUseCase = searchMoviesUseCase;
        this.deleteMovieUseCase = deleteMovieUseCase;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return listMoviesUseCase.execute();
    }

    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        return createMovieUseCase.execute(request);
    }

    @Override
    public List<MovieEntity> searchMoviesByTitle(String title) {
        return searchMoviesUseCase.execute(title);
    }

    @Override
    public void deleteMovie(Integer id) {
        deleteMovieUseCase.execute(id);
    }

}

