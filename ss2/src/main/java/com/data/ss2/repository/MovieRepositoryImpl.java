package com.data.ss2.repository;

import com.data.ss2.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findByTitleContainingIgnoreCase(String customParam) {
        String jpql = "SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(:param)";
        return entityManager.createQuery(jpql, Movie.class)
                .setParameter("param", "%" + customParam + "%")
                .getResultList();
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            entityManager.persist(movie);
            return movie;
        } else {
            return entityManager.merge(movie);
        }
    }

    @Override
    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        String jpql = "SELECT m FROM Movie m";
        return entityManager.createQuery(jpql, Movie.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }
}
