package com.fatma.movie_app.controller;

import com.fatma.movie_app.model.dto.MovieRequest;
import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.entity.Movie;
import com.fatma.movie_app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public MovieResponse addMovie(@RequestBody MovieRequest movie) {
        return movieService.addMovie(movie);
    }
    @PutMapping("/update/{id}")
    public MovieResponse updateMovie(@RequestBody MovieRequest movie, @PathVariable long id) {
        return movieService.updateMovie(movie,id);
    }
    @GetMapping("/getMovie/{id}")
    public MovieResponse getMovieById(@PathVariable long id) {
        return movieService.getMovieById(id);
    }
    @GetMapping("/getAllMovies")
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();

    }
@DeleteMapping("/removeMovie/{id}")
    public void removeMovieById(@PathVariable long id) {
         movieService.removeMovieById(id);

    }
    @DeleteMapping("/removeAllMovies")
    public void removeAllMovies() {
        movieService.removeAllMovies();
    }
    @GetMapping("/search")
    public List<MovieResponse> findMoviesByTitle(@RequestParam String title){
      return   movieService.findMoviesByTitle(title);
    }
    public ResponseEntity<?> addRate(@RequestParam long movieId, @RequestParam double rate){
        movieService.addRate(movieId,rate);
        return new ResponseEntity<>("add rate done", HttpStatus.OK);

    }

}

