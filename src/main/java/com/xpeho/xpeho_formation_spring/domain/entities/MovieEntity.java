package com.xpeho.xpeho_formation_spring.domain.entities;

/**
 * Domain entity representing a movie in the business domain layer.
 *
 * This immutable record class encapsulates the core movie information used throughout
 * the application domain logic. It is independent of any persistence mechanism and
 * represents the canonical movie representation for business operations.
 *
 * @author XPEHO
 * @see Movie
 */
public record MovieEntity(
        /**
         * Unique identifier for the movie.
         */
        int id,
        /**
         * The title of the movie.
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
