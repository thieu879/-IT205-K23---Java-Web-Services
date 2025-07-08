package com.data.ss2.repository;

import com.data.ss2.entity.Showtime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ShowtimeRepositoryImpl implements ShowtimeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Showtime> findByMovieId(Long movieId) {
        String jpql = "SELECT st FROM Showtime st WHERE st.movie.id = :movieId";
        return entityManager.createQuery(jpql, Showtime.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }

    @Override
    public List<Showtime> findByScreenRoomId(Long screenRoomId) {
        String jpql = "SELECT st FROM Showtime st WHERE st.screenRoom.id = :screenRoomId";
        return entityManager.createQuery(jpql, Showtime.class)
                .setParameter("screenRoomId", screenRoomId)
                .getResultList();
    }

    @Override
    @Transactional
    public Showtime save(Showtime showtime) {
        if (showtime.getId() == null) {
            entityManager.persist(showtime);
            return showtime;
        }
        return entityManager.merge(showtime);
    }

    @Override
    public Showtime findById(Long id) {
        return entityManager.find(Showtime.class, id);
    }

    @Override
    public List<Showtime> findAll() {
        String jpql = "SELECT st FROM Showtime st";
        return entityManager.createQuery(jpql, Showtime.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Showtime showtime = entityManager.find(Showtime.class, id);
        if (showtime != null) {
            entityManager.remove(showtime);
        }
    }
    @Override
    public List<Showtime> filterShowtimes(Long movieId, Long screenRoomId, LocalDate date) {
        String jpql = "SELECT s FROM Showtime s WHERE "
                + "(:movieId IS NULL OR s.movie.id = :movieId) AND "
                + "(:screenRoomId IS NULL OR s.screenRoom.id = :screenRoomId) AND "
                + "(:date IS NULL OR FUNCTION('DATE', s.startTime) = :date)";
        return entityManager.createQuery(jpql, Showtime.class)
                .setParameter("movieId", movieId)
                .setParameter("screenRoomId", screenRoomId)
                .setParameter("date", date)
                .getResultList();
    }

}
