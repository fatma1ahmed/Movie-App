package com.fatma.movie_app.service;

import com.fatma.movie_app.exception.RecordNotCorrectException;
import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.entity.Movie;
import com.fatma.movie_app.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private RestTemplate restTemplate;
    private final String OMDb_API_url = "http://www.omdbapi.com/?apikey=365e2720&";

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public Movie getMovieById(long id) {
        return movieRepo.findById(id).orElseThrow(
                () -> new RecordNotCorrectException("movie with Id " + id + "not found")
        );
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        if (!movies.isEmpty() && movies != null)
            return movies;
        else throw new RecordNotCorrectException("All movies not found");
    }

    public void removeMovieById(long id) {
        getMovieById(id);
        movieRepo.deleteById(id);
    }

    public void removeAllMovies() {
        movieRepo.deleteAll();
    }

    public Movie fetchMovieFromOMDb(String title) {
        String url = OMDb_API_url + "t=" + title;

        MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
        if (response!= null) {
            Movie movie=new Movie();
            movie.setTitle(response.getTitle());
            movie.setYear(response.getYear());
            movie.setPlot(response.getPlot());
            movie.setPoster(response.getPoster());
            return movie;
        } else {
            throw new RecordNotCorrectException("Movie not found in OMDB API ");
        }
    }
}
