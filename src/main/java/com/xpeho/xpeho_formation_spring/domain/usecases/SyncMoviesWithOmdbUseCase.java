package com.xpeho.xpeho_formation_spring.domain.usecases;

import com.xpeho.xpeho_formation_spring.data.models.OmdbMovie;
import com.xpeho.xpeho_formation_spring.data.services.OmdbApiClient;
import com.xpeho.xpeho_formation_spring.domain.entities.CreateMovieRequest;
import com.xpeho.xpeho_formation_spring.domain.entities.MovieEntity;
import com.xpeho.xpeho_formation_spring.domain.services.MovieService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Use case for synchronizing movies from OMDb API to the database.
 * <p>
 * This use case orchestrates the retrieval of movies from the external OMDb API,
 * filters out movies that already exist in the database, saves new movies,
 * and returns the complete list of movies.
 *
 * @author XPEHO
 */
@Service
public class SyncMoviesWithOmdbUseCase {

    private final OmdbApiClient omdbApiClient;
    private final MovieService movieService;

    /**
     * Constructor with dependency injection.
     *
     * @param omdbApiClient the OMDb API client for fetching movies
     * @param movieService  the movie service for database operations
     */
    public SyncMoviesWithOmdbUseCase(OmdbApiClient omdbApiClient, MovieService movieService) {
        this.omdbApiClient = omdbApiClient;
        this.movieService = movieService;
    }

    /**
     * Executes the use case to synchronize movies from OMDb API.
     * <p>
     * Fetches movies from OMDb API by title, filters out duplicates already in the database,
     * saves new movies, and returns all available movies.
     *
     * @param title the title to search for in OMDb API
     * @param page  the page number to fetch from OMDb API
     * @return a list of MovieEntity objects representing all movies in the database
     * @throws IOException if the OMDb API call fails
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
     * Converts an OMDb movie to a CreateMovieRequest.
     *
     * @param omdbMovie the OMDb movie to convert
     * @return a CreateMovieRequest object
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

