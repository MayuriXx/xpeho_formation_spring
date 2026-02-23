package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.data.converters.MovieConverter;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class SearchMoviesUseCase {

    private final MovieService service;
    private final MovieConverter converter;

    public SearchMoviesUseCase(MovieService service, MovieConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    public List<MovieEntity> execute(String title) {
        return StreamSupport.stream(service.searchByTitle(title).spliterator(), false)
                .map(converter::modelToEntity)
                .toList();
    }
}

