package com.data.ss2.repository;

import com.data.ss2.entity.Theater;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TheaterRepositoryImpl implements TheaterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Theater> findByNameContainingIgnoreCase(String name) {
        String jpql = "SELECT t FROM Theater t WHERE LOWER(t.name) LIKE LOWER(:name)";
        return entityManager.createQuery(jpql, Theater.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<Theater> findByAddressContainingIgnoreCase(String address) {
        String jpql = "SELECT t FROM Theater t WHERE LOWER(t.address) LIKE LOWER(:address)";
        return entityManager.createQuery(jpql, Theater.class)
                .setParameter("address", "%" + address + "%")
                .getResultList();
    }

    @Override
    @Transactional
    public Theater save(Theater theater) {
        if (theater.getId() == null) {
            entityManager.persist(theater);
            return theater;
        }
        return entityManager.merge(theater);
    }

    @Override
    public Theater findById(Long id) {
        return entityManager.find(Theater.class, id);
    }

    @Override
    public List<Theater> findAll() {
        String jpql = "SELECT t FROM Theater t";
        return entityManager.createQuery(jpql, Theater.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Theater theater = entityManager.find(Theater.class, id);
        if (theater != null) {
            entityManager.remove(theater);
        }
    }
}
