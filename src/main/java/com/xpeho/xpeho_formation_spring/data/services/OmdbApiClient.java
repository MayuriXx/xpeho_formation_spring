package com.xpeho.xpeho_formation_spring.data.services;

import com.xpeho.xpeho_formation_spring.data.models.OmdbSearchResponse;
import com.xpeho.xpeho_formation_spring.domain.services.OmdbApiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client for the OMDb external API using Retrofit.
 * <p>
 * This service handles all communication with the OMDb API.
 * It configures a Retrofit instance with a base URL, a Gson converter,
 * and an OkHttp client with connection/read/write timeouts to avoid
 * indefinite blocking calls.
 * <p>
 * The API key is injected from the {@code omdb.api.key} property,
 * which is resolved from the {@code OMDB_API_KEY} environment variable
 * defined in the {@code .env} file via spring-dotenv.
 *
 * @author XPEHO
 * @see OmdbApiService
 */
@Service
public class OmdbApiClient {
    private final OmdbApiService omdbApiService;
    private final String apiKey;

    /**
     * Constructs the OmdbApiClient with the given API key.
     * <p>
     * Initializes the OkHttp client with 10-second timeouts and
     * builds the Retrofit instance targeting {@code http://www.omdbapi.com/}.
     *
     * @param apiKey the OMDb API key injected from {@code omdb.api.key}
     */
    public OmdbApiClient(@Value("${omdb.api.key}") String apiKey) {
        this.apiKey = apiKey;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.omdbApiService = retrofit.create(OmdbApiService.class);
    }

    /**
     * Searches for movies on the OMDb API by title and page number.
     * <p>
     * Performs a synchronous HTTP call to the OMDb search endpoint.
     * Results are filtered to type "movie" by the {@link OmdbApiService} annotation.
     *
     * @param title the title to search for
     * @param page  the page number of results to retrieve (1-based)
     * @return an {@link OmdbSearchResponse} containing up to 10 results, or {@code null} if the call fails
     * @throws IOException if a network error occurs during the API call
     */
    public OmdbSearchResponse searchMovies(String title, int page) throws IOException {
        return omdbApiService.searchMovies(title, apiKey, page).execute().body();
    }
}
