package com.xpeho.xpeho_formation_spring.domain.services;

import com.xpeho.xpeho_formation_spring.data.models.OmdbMovie;
import com.xpeho.xpeho_formation_spring.data.models.OmdbSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiService {
    @GET("/?type=movie")
    Call<OmdbSearchResponse> searchMovies(
            @Query("s") String title,
            @Query("apikey") String apiKey
    );

    @GET("/")
    Call<OmdbMovie> getMovieById(
            @Query("i") String imdbId,
            @Query("apikey") String apiKey
    );
}
