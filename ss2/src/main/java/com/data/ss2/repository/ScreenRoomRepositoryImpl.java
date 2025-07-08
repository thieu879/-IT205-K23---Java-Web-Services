package com.data.ss2.repository;

import com.data.ss2.entity.ScreenRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScreenRoomRepositoryImpl implements ScreenRoomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScreenRoom> findByNameContainingIgnoreCase(String name) {
        String jpql = "SELECT sr FROM ScreenRoom sr WHERE LOWER(sr.name) LIKE LOWER(:name)";
        return entityManager.createQuery(jpql, ScreenRoom.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<ScreenRoom> findByTheaterId(Long theaterId) {
        String jpql = "SELECT sr FROM ScreenRoom sr WHERE sr.theater.id = :theaterId";
        return entityManager.createQuery(jpql, ScreenRoom.class)
                .setParameter("theaterId", theaterId)
                .getResultList();
    }

    @Override
    @Transactional
    public ScreenRoom save(ScreenRoom screenRoom) {
        if (screenRoom.getId() == null) {
            entityManager.persist(screenRoom);
            return screenRoom;
        }
        return entityManager.merge(screenRoom);
    }

    @Override
    public ScreenRoom findById(Long id) {
        return entityManager.find(ScreenRoom.class, id);
    }

    @Override
    public List<ScreenRoom> findAll() {
        String jpql = "SELECT sr FROM ScreenRoom sr";
        return entityManager.createQuery(jpql, ScreenRoom.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ScreenRoom screenRoom = entityManager.find(ScreenRoom.class, id);
        if (screenRoom != null) {
            entityManager.remove(screenRoom);
        }
    }
}
