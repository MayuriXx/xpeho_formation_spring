package com.xpeho.xpeho_formation_spring.domain.entities;

/**
 * DTO for updating an existing movie.
 *
 * This immutable record class captures the data required to update a movie.
 * It is used as the request body in the movie update endpoint and contains
 * all fields that can be modified in an update operation.
 *
 * @author XPEHO
 */
public record UpdateMovieRequest(
        /**
         * The updated title of the movie.
         */
        String title,
        /**
         * The updated release year of the movie.
         */
        String year,
        /**
         * The updated IMDb identifier for the movie.
         */
        String imdbId,
        /**
         * The updated type of content (e.g., "movie", "series").
         */
        String type,
        /**
         * The updated URL to the movie poster image.
         */
        String poster
) {
}
