package com.fatma.movie_app.repository;

import com.fatma.movie_app.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    List<Movie> findByTitle(String title);

}
