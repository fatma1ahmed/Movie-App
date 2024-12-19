package com.fatma.movie_app.controller;

import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.entity.Movie;
import com.fatma.movie_app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/fetchMovieFromOMDb")
    public MovieResponse fetchMovieFromOMDb(@RequestParam String title){
        return movieService.fetchMovieFromOMDb(title);
    }
    @GetMapping("/fetchMovieListFromOMDb")
    public List<MovieResponse> fetchMovieListFromOMDb(@RequestParam String title) {
        return movieService.fetchMovieListFromOMDb(title);
    }
@PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
    @GetMapping("/getMovie/{id}")
    public Movie getMovieById(@PathVariable long id) {
        return movieService.getMovieById(id);
    }
    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();

    }
@DeleteMapping("/removeMovieById/{id}")
    public void removeMovieById(@PathVariable long id) {
         movieService.removeMovieById(id);

    }
    @DeleteMapping("/removeAllMovies")
    public void removeAllMovies() {
        movieService.removeAllMovies();
    }

}

