package com.data.ss14.service;

import com.data.ss14.model.dto.request.MovieRequestDTO;
import com.data.ss14.model.entity.Movie;

import java.util.List;

public interface MovieService{
    List<Movie> getAllMovies();
    Movie addMovie(MovieRequestDTO movieRequestDTO);
    Movie updateMovie(Long id, MovieRequestDTO movieRequestDTO);
    void deleteMovie(Long id);
}