package com.xpeho.xpeho_formation_spring.data.services;

import com.xpeho.xpeho_formation_spring.data.models.OmdbSearchResponse;
import com.xpeho.xpeho_formation_spring.domain.services.OmdbApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class OmdbApiClient {
    private final OmdbApiService omdbApiService;
    private final String apiKey;

    public OmdbApiClient(@Value("${omdb.api.key}") String apiKey) {
        this.apiKey = apiKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.omdbApiService = retrofit.create(OmdbApiService.class);
    }

    public OmdbSearchResponse searchMovies(String title) throws IOException {
        return omdbApiService.searchMovies(title, apiKey).execute().body();
    }
}
