package com.xpeho.xpeho_formation_spring.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MOVIE")
public record Movie(
        @Id int id,
        String title,
        @Column("year") String year,
        @Column("imdb_id") String imdbId,
        @Column("type") String type,
        String poster
) {
}

