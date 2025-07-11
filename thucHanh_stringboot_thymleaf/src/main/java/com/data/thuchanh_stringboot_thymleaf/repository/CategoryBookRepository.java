package com.data.thuchanh_stringboot_thymleaf.repository;

import com.data.thuchanh_stringboot_thymleaf.entity.CategoryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryBookRepository extends JpaRepository<CategoryBook, Long> {
    List<CategoryBook> findByNameContainingIgnoreCase(String name);
}
