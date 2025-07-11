package com.data.thuchanh_stringboot_thymleaf.service;

import com.data.thuchanh_stringboot_thymleaf.entity.CategoryBook;
import com.data.thuchanh_stringboot_thymleaf.repository.CategoryBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryBookServiceImpl implements CategoryBookService {
    private CategoryBookRepository categoryBookRepository;
    public CategoryBookServiceImpl(CategoryBookRepository categoryBookRepository) {
        this.categoryBookRepository = categoryBookRepository;
    }
    @Override
    public List<CategoryBook> findAllCategoryBooks() {
        return categoryBookRepository.findAll();
    }

    @Override
    public CategoryBook findCategoryBookById(Long id) {
        try {
            return categoryBookRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveCategoryBook(CategoryBook categoryBook) {
        try {
            categoryBookRepository.save(categoryBook);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCategoryBookById(Long id) {
        try {
            categoryBookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCategoryBook(CategoryBook categoryBook) {
        try {
            categoryBookRepository.save(categoryBook);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CategoryBook> findByCategoryBookName(String name) {
        try {
            return categoryBookRepository.findByNameContainingIgnoreCase(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
