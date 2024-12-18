package com.fatma.movie_app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Poster")
    private String poster;
    public String getTitle() {
        return this.title;
    }
    public String getYear() {
        return this.year;
    }
    public String getPlot() {
        return this.plot;
    }
    public String getPoster() {
        return this.poster;
    }




}
