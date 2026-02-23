package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.UpdateMovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieEntity> listMovies();

    MovieEntity createMovie(CreateMovieRequest request);

    Iterable<Movie> searchByTitle(String title);

    MovieEntity putMovie(Integer id, UpdateMovieRequest request);

    void deleteMovie(Integer id);

    MovieEntity getMovieById(Integer id);

}

