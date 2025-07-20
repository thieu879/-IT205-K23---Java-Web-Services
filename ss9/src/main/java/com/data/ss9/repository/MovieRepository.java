package com.data.ss9.repository;

import com.data.ss9.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:searchMovie%")
    List<Movie> findByTitleContaining(@Param("searchMovie") String searchMovie);
}
