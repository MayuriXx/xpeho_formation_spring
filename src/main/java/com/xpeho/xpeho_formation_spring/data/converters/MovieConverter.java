package com.xpeho.xpeho_formation_spring.data.converters;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieConverter {

    public MovieEntity modelToEntity(Movie model) {
        return new MovieEntity(model.id(), model.title(), model.year(), model.imdbId(), model.type(), model.poster());
    }

    public Movie entityToModel(MovieEntity entity) {
        return new Movie(entity.id(), entity.title(), entity.year(), entity.imdbId(), entity.type(), entity.poster());
    }
}

