package com.data.ss2.repository;

import com.data.ss2.entity.Movie;
import java.util.List;

public interface MovieRepository {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    Movie save(Movie movie);
    Movie findById(Long id);
    List<Movie> findAll();
    void deleteById(Long id);
}
