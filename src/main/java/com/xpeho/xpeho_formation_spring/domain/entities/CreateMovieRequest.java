package com.xpeho.xpeho_formation_spring.domain.entities;

public record CreateMovieRequest(String title, String year, String imdbId, String type, String poster) {
}
