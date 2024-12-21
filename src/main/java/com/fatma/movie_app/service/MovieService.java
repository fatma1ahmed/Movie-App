package com.fatma.movie_app.service;

import com.fatma.movie_app.exception.RecordNotCorrectException;
import com.fatma.movie_app.mapper.MovieMapper;
import com.fatma.movie_app.mapper.MovieSearchMapper;
import com.fatma.movie_app.model.dto.MovieRequest;
import com.fatma.movie_app.model.dto.MovieResponse;
import com.fatma.movie_app.model.dto.MovieSearchResponse;
import com.fatma.movie_app.model.entity.Movie;
import com.fatma.movie_app.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MovieSearchMapper movieSearchMapper;
//    @Value("${OMDb_API_URL}")
    private final   String OMDb_API_URL = "http://www.omdbapi.com/?apikey=365e2720&";

        public MovieResponse addMovie(MovieRequest movieRequest) {
            Movie movie=movieMapper.toEntity(movieRequest);
        return movieMapper.toResponse(movieRepo.save(movie));
    }
    public MovieResponse updateMovie(MovieRequest movieRequest,long id) {
       getMovieById(id);
        Movie movie=movieMapper.toEntity(movieRequest);
       movie.setId(id);
        return movieMapper.toResponse(movieRepo.save(movie));
    }

    public Movie getEntityMovieById(long id) {
        return movieRepo.findById(id).orElseThrow(
                () -> new RecordNotCorrectException("movie with Id " + id + "not found")
        );
    }
    public MovieResponse getMovieById(long id){
          return movieMapper.toResponse(getEntityMovieById(id));
    }

    public List<MovieResponse> getAllMovies() {

            return movieRepo.findAll().stream().map(movieMapper::toResponse).collect(Collectors.toList());
    }

    public void removeMovieById(long id) {
        getMovieById(id);
        movieRepo.deleteById(id);
    }

    public void removeAllMovies() {
        movieRepo.deleteAll();
    }

    public MovieResponse fetchMovieFromOMDb(String title) {
        String url = OMDb_API_URL + "t=" + title;

        MovieResponse movieResponse = restTemplate.getForObject(url, MovieResponse.class);
        if (movieResponse != null && "True".equals(movieResponse.getResponse())) {
            return movieResponse;

        } else {
            throw new RecordNotCorrectException("Movie not found in OMDB API ");
        }
    }
public List<MovieResponse> fetchMovieListFromOMDb(String title) {
    String url = OMDb_API_URL + "s=" + title;
    MovieSearchResponse movieSearchResponse = restTemplate.getForObject(url, MovieSearchResponse.class);
    if (movieSearchResponse != null && "True".equalsIgnoreCase(movieSearchResponse.getResponse())) {
       List<MovieResponse> movieResponseList= movieSearchResponse.getMovieResponseList();
        if (movieResponseList != null && !movieResponseList.isEmpty()) {
            movieResponseList.forEach(movieResponse -> {
                if ( movieResponse.getPlot() == null) {
                    movieResponse.setPlot("N/A");
                }
                    if(movieResponse.getResponse()==null) {
                        movieResponse.setResponse("True");
                    }

            });
          List<Movie> movies=  movieSearchMapper.toEntities(movieResponseList);
            return movieSearchMapper.toResponses(movies) ;
        } else {
            throw new RecordNotCorrectException("No movies found in the search results.");
        }
    }

    else {
        throw new RecordNotCorrectException("Movies not found in OMDB API for title: " + title);
    }
}
public List<MovieResponse> findMoviesByTitle(String title){
        return movieRepo.findByTitle(title).stream().map(movieMapper::toResponse).collect(Collectors.toList());

}
public void addRate(long movieId,double rate){
        Movie movie=getEntityMovieById(movieId);
        movie.setRate(rate);
        movieRepo.save(movie);
}
}
