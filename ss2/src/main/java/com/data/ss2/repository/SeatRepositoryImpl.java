package com.data.ss2.repository;

import com.data.ss2.entity.Seat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatRepositoryImpl implements SeatRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Seat> findBySeatNumber(String seatNumber) {
        String jpql = "SELECT s FROM Seat s WHERE s.seatNumber = :seatNumber";
        return entityManager.createQuery(jpql, Seat.class)
                .setParameter("seatNumber", seatNumber)
                .getResultList();
    }

    @Override
    public List<Seat> findByScreenRoomId(Long screenRoomId) {
        String jpql = "SELECT s FROM Seat s WHERE s.screenRoom.id = :screenRoomId";
        return entityManager.createQuery(jpql, Seat.class)
                .setParameter("screenRoomId", screenRoomId)
                .getResultList();
    }

    @Override
    @Transactional
    public Seat save(Seat seat) {
        if (seat.getId() == null) {
            entityManager.persist(seat);
            return seat;
        } else {
            return entityManager.merge(seat);
        }
    }

    @Override
    public Seat findById(Long id) {
        return entityManager.find(Seat.class, id);
    }

    @Override
    public List<Seat> findAll() {
        String jpql = "SELECT s FROM Seat s";
        return entityManager.createQuery(jpql, Seat.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Seat seat = entityManager.find(Seat.class, id);
        if (seat != null) {
            entityManager.remove(seat);
        }
    }
}
