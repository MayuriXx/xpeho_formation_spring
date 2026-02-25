package com.xpeho.xpeho_formation_spring.domain.entities;

/**
 * DTO for creating a new movie.
 * <p>
 * This immutable record class captures the data required to create a new movie.
 * It is used as the request body in the movie creation endpoint and contains
 * all mandatory fields for a movie creation operation.
 *
 * @author XPEHO
 */
public record CreateMovieRequest(
        /**
         * The title of the movie to create.
         */
        String title,
        /**
         * The release year of the movie.
         */
        String year,
        /**
         * The IMDb identifier for the movie.
         */
        String imdbId,
        /**
         * The type of content (e.g., "movie", "series").
         */
        String type,
        /**
         * URL to the movie poster image.
         */
        String poster
) {
}
