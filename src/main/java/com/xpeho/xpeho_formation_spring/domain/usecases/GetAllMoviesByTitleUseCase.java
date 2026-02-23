package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.data.converters.MovieConverter;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Use case for searching movies by title.
 *
 * This use case encapsulates the business logic for searching movies in the system
 * based on a partial title match (case-insensitive).
 *
 * @author XPEHO
 */
@Service
public class GetAllMoviesByTitleUseCase {

    private final MovieService service;
    private final MovieConverter converter;

    /**
     * Constructor with dependency injection.
     *
     * @param service the movie service for search operations
     * @param converter the converter for transforming models to entities
     */
    public GetAllMoviesByTitleUseCase(MovieService service, MovieConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    /**
     * Executes the use case to search movies by title.
     *
     * @param title the title text to search for (partial, case-insensitive match)
     * @return a list of MovieEntity objects matching the search criteria
     */
    public List<MovieEntity> execute(String title) {
        return StreamSupport.stream(service.searchByTitle(title).spliterator(), false)
                .map(converter::modelToEntity)
                .toList();
    }
}
