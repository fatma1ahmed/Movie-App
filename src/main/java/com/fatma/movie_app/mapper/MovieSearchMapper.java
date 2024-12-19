package com.fatma.movie_app.mapper;

import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")

public interface MovieSearchMapper {
    @Mapping(target = "response", constant = "True")
    @Mapping(target = "plot", constant = "N/A")
    List<MovieResponse> toResponses(List<Movie> movie);

    List<Movie> toEntities(List<MovieResponse> moviesResponse);
}
