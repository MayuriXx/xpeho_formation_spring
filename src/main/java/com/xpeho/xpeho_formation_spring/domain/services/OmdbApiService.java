package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.data.models.OmdbSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface defining the OMDb API contract.
 * <p>
 * This interface declares the HTTP endpoints exposed by the external OMDb API.
 * Retrofit uses it to generate a type-safe HTTP client at runtime.
 * All requests target the base URL {@code http://www.omdbapi.com/} configured
 * in {@link com.xpeho.xpeho_formation_spring.data.services.OmdbApiClient}.
 *
 * @author XPEHO
 * @see com.xpeho.xpeho_formation_spring.data.services.OmdbApiClient
 */
public interface OmdbApiService {

    /**
     * Searches for movies on the OMDb API.
     * <p>
     * Sends a GET request to {@code /?s={title}&apikey={apiKey}&page={page}&type=movie}.
     * Results are automatically filtered to type "movie" via the fixed query parameter.
     *
     * @param title  the title keyword to search for
     * @param apiKey the OMDb API key for authentication
     * @param page   the page number of results to retrieve (1-based, 10 results per page)
     * @return a Retrofit {@link Call} wrapping an {@link OmdbSearchResponse}
     */
    @GET("/?type=movie")
    Call<OmdbSearchResponse> searchMovies(
            @Query("s") String title,
            @Query("apikey") String apiKey,
            @Query("page") int page
    );
}
