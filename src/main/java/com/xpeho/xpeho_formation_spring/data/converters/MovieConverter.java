package com.xpeho.xpeho_formation_spring.data.converters;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import org.springframework.stereotype.Service;

/**
 * Converter for transforming between Movie and MovieEntity.
 * <p>
 * This service provides bidirectional conversion between the data layer model (Movie)
 * and the domain entity (MovieEntity). It acts as a bridge between the persistence
 * layer and the business logic layer, ensuring proper separation of concerns.
 *
 * @author XPEHO
 */
@Service
public class MovieConverter {

    /**
     * Converts a Movie (data layer) to a MovieEntity (domain layer).
     *
     * @param model the Movie object from the database layer
     * @return a MovieEntity representing the same movie in the domain layer
     */
    public MovieEntity modelToEntity(Movie model) {
        return new MovieEntity(model.id(), model.title(), model.year(), model.imdbId(), model.type(), model.poster());
    }

    /**
     * Converts a MovieEntity (domain layer) to a Movie (data layer).
     *
     * @param entity the MovieEntity from the domain layer
     * @return a Movie object for persistence in the database
     */
    public Movie entityToModel(MovieEntity entity) {
        return new Movie(entity.id(), entity.title(), entity.year(), entity.imdbId(), entity.type(), entity.poster());
    }
}
