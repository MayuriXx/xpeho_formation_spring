package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.data.models.OmdbMovie;
import com.xpeho.xpeho_formation_spring.data.services.OmdbApiClient;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.errors.OmdbApiException;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Use case for synchronizing movies from OMDb API to the database.
 * <p>
 * This use case orchestrates the retrieval of movies from the external OMDb API
 * for a given title and page number, saves each new movie via {@link MovieService},
 * and returns the complete list of movies stored in the database.
 * <p>
 * Duplicate movies (already existing in the database) are silently skipped
 * to ensure idempotency of the synchronization.
 *
 * @author XPEHO
 * @see com.xpeho.xpeho_formation_spring.data.services.OmdbApiClient
 * @see MovieService
 */
@Service
public class SyncMoviesWithOmdbUseCase {

    private final OmdbApiClient omdbApiClient;
    private final MovieService movieService;

    /**
     * Constructor with dependency injection.
     *
     * @param omdbApiClient the OMDb API client for fetching movies from the external API
     * @param movieService  the movie service for database operations
     */
    public SyncMoviesWithOmdbUseCase(OmdbApiClient omdbApiClient, MovieService movieService) {
        this.omdbApiClient = omdbApiClient;
        this.movieService = movieService;
    }

    /**
     * Executes the synchronization of movies from OMDb API for the given title and page.
     * <p>
     * Fetches up to 10 movies per page from OMDb API, attempts to save each one
     * into the database, and returns the full list of movies after synchronization.
     * Movies that already exist or cause an error are skipped and logged to stderr.
     *
     * @param title the title keyword to search for in OMDb API
     * @param page  the page number to fetch from OMDb API (1-based, 10 results per page)
     * @return the complete list of {@link MovieEntity} objects stored in the database
     * @throws IOException if the HTTP call to the OMDb API fails
     */
    public List<MovieEntity> execute(String title, int page) throws IOException {
        var omdbResponse = omdbApiClient.searchMovies(title, page);

        if (omdbResponse != null && omdbResponse.getSearch() != null) {
            omdbResponse.getSearch().forEach(omdbMovie -> {
                try {
                    // Simply save the movie - duplicates will be handled by unique constraint if needed
                    movieService.createMovie(convertOmdbMovieToRequest(omdbMovie));
                } catch (Exception e) {
                    // Movie might already exist or other error occurred, just continue
                    System.err.println("Movie skipped: " + omdbMovie.getTitle() + " - " + e.getMessage());
                }
            });
        }

        return movieService.listMovies();
    }

    /**
     * Converts an {@link OmdbMovie} from the OMDb API response into a {@link CreateMovieRequest}.
     *
     * @param omdbMovie the OMDb movie object to convert
     * @return a {@link CreateMovieRequest} populated with the OMDb movie data
     */
    private CreateMovieRequest convertOmdbMovieToRequest(OmdbMovie omdbMovie) {
        return new CreateMovieRequest(
                omdbMovie.getTitle(),
                omdbMovie.getYear(),
                omdbMovie.getImdbID(),
                omdbMovie.getType(),
                omdbMovie.getPoster()
        );
    }
}
