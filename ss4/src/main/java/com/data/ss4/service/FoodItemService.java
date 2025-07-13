package com.data.ss4.service;

import com.data.ss4.entity.Category;
import com.data.ss4.entity.FoodItem;
import com.data.ss4.repository.CategoryRepository;
import com.data.ss4.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<FoodItem> findAll(Pageable pageable) {
        return foodItemRepository.findAll(pageable);
    }

    public Optional<FoodItem> findById(Long id) {
        return foodItemRepository.findById(id);
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public void deleteById(Long id) {
        foodItemRepository.deleteById(id);
    }

    public Page<FoodItem> searchByName(String name, Pageable pageable) {
        return foodItemRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Page<FoodItem> filterByCategory(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return foodItemRepository.findByCategory(category, pageable);
    }

    public Page<FoodItem> searchAndFilter(String name, Long categoryId, Pageable pageable) {
        if (name != null && !name.isEmpty() && categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            return foodItemRepository.findByNameContainingIgnoreCaseAndCategory(name, category, pageable);
        } else if (name != null && !name.isEmpty()) {
            return searchByName(name, pageable);
        } else if (categoryId != null) {
            return filterByCategory(categoryId, pageable);
        } else {
            return findAll(pageable);
        }
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
