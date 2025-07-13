package com.data.ss4.repository;

import com.data.ss4.entity.Category;
import com.data.ss4.entity.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Page<FoodItem> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<FoodItem> findByCategory(Category category, Pageable pageable);

    Page<FoodItem> findByNameContainingIgnoreCaseAndCategory(String name, Category category, Pageable pageable);

    List<FoodItem> findByExpirationDateBefore(LocalDate date);
}
