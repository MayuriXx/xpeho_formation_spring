create table if not exists MOVIE (
    id int auto_increment primary key,
    title varchar(255) not null,
    "year" varchar(4),
    "imdb_id" varchar(50),
    "type" varchar(50),
    poster varchar(500)
);
