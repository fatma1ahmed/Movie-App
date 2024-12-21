package com.fatma.movie_app.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatma.movie_app.model.entity.Movie;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    @JsonProperty("movieId")
    private long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Response")
    private String response;
}

