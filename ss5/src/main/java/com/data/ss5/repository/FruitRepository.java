package com.data.ss5.repository;

import com.data.ss5.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findByStatus(Boolean status);
    List<Fruit> findByNameContainingIgnoreCase(String name);
    @Query("SELECT f FROM Fruit f WHERE f.price BETWEEN :minPrice AND :maxPrice")
    List<Fruit> findByPriceRange(Double minPrice, Double maxPrice);
}

