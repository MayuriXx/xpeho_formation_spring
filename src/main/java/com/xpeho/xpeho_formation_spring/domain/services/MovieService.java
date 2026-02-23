package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.data.models.Movie;

import java.util.List;

public interface MovieService {

    List<MovieEntity> listMovies();

    MovieEntity createMovie(CreateMovieRequest request);

    Iterable<Movie> searchByTitle(String title);

    void deleteMovie(Integer id);

}

