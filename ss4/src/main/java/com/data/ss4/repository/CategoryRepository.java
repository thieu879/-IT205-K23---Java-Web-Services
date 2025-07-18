package com.data.ss4.repository;

import com.data.ss4.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
    Page<Category> findByCategoryNameContainingIgnoreCase(String categoryName, Pageable pageable);
}
