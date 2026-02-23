package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.data.converters.MovieConverter;
import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.data.sources.MovieRepository;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final MovieConverter converter;

    public MovieServiceImpl(MovieRepository repository, MovieConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<MovieEntity> listMovies() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(converter::modelToEntity)
                .toList();
    }

    @Override
    public MovieEntity createMovie(CreateMovieRequest request) {
        var newMovie = new Movie(0, request.title(), request.year(), request.imdbId(), request.type(), request.poster());
        var savedMovie = repository.save(newMovie);
        return converter.modelToEntity(savedMovie);
    }

    @Override
    public Iterable<Movie> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void deleteMovie(Integer id) {
        repository.deleteById(id);
    }

}

