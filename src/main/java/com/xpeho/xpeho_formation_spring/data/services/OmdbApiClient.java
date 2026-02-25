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

@Service
public class OmdbApiClient {
    private final OmdbApiService omdbApiService;
    private final String apiKey;

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

    public OmdbSearchResponse searchMovies(String title, int page) throws IOException {
        return omdbApiService.searchMovies(title, apiKey, page).execute().body();
    }
}


