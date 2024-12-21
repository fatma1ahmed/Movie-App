package com.fatma.movie_app.service;

import com.fatma.movie_app.model.dto.MovieRequest;
import com.fatma.movie_app.model.dto.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MovieService {

    public MovieResponse fetchMovieFromOMDb(String title);


    public List<MovieResponse> fetchMovieListFromOMDb( String title) ;

    public MovieResponse addMovie( MovieRequest movie) ;

    public MovieResponse updateMovie(MovieRequest movie,long id);

    public MovieResponse getMovieById( long id);
    public List<MovieResponse> getAllMovies() ;

    public void removeMovieById(long id) ;

    public void removeAllMovies() ;
    public List<MovieResponse> findMoviesByTitle( String title);
    public void addRate( long movieId,double rate);

}
