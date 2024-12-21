package com.fatma.movie_app.controller;

import com.fatma.movie_app.model.dto.MovieRequest;
import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MovieController {

    private final MovieService movieService;
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
    public ResponseEntity<?> removeMovieById(@PathVariable long id) {
         movieService.removeMovieById(id);
    return new ResponseEntity<>("movie with id: "+ id + "deleted", HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/removeAllMovies")
    public ResponseEntity<?> removeAllMovies() {
        movieService.removeAllMovies();
        return new ResponseEntity<>("All movies deleted successfull", HttpStatus.ACCEPTED);
    }
    @GetMapping("/search")
    public List<MovieResponse> findMoviesByTitle(@RequestParam String title){
      return   movieService.findMoviesByTitle(title);
    }
    @PostMapping("/rateMovie")
    public ResponseEntity<?> addRate(@RequestParam long movieId, @RequestParam double rate){
        movieService.addRate(movieId,rate);
        return new ResponseEntity<>("add rate done", HttpStatus.OK);

    }

}

