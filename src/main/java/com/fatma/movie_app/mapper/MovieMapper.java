package com.fatma.movie_app.mapper;
import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "response", constant = "True")
    MovieResponse toResponse(Movie movie);
    Movie toEntity(MovieResponse movieResponse);




}
