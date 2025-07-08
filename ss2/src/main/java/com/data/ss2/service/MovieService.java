package com.data.ss2.service;

import com.data.ss2.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    Movie save(Movie movie);
    Movie findById(Long id);
    List<Movie> findAll();
    void deleteById(Long id);
}
