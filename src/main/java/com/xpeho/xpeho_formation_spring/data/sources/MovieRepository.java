package com.xpeho.xpeho_formation_spring.data.sources;

import com.xpeho.xpeho_formation_spring.data.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    Iterable<Movie> findByTitleContainingIgnoreCase(String title);
}

