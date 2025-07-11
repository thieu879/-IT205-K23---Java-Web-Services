package com.data.thuchanh_stringboot_thymleaf.service;

import com.data.thuchanh_stringboot_thymleaf.entity.CategoryBook;

import java.util.List;

public interface CategoryBookService {
    List<CategoryBook> findAllCategoryBooks();
    CategoryBook findCategoryBookById(Long id);
    boolean saveCategoryBook(CategoryBook categoryBook);
    boolean deleteCategoryBookById(Long id);
    boolean updateCategoryBook(CategoryBook categoryBook);
    List<CategoryBook> findByCategoryBookName(String name);
}
