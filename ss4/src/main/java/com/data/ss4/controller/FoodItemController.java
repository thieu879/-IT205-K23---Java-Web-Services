package com.data.ss4.controller;

import com.data.ss4.entity.FoodItem;
import com.data.ss4.service.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    // Hiển thị danh sách thực phẩm với phân trang
    @GetMapping
    public String listFoodItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FoodItem> foodItems = foodItemService.searchAndFilter(name, categoryId, pageable);

        model.addAttribute("foodItems", foodItems);
        model.addAttribute("categories", foodItemService.findAllCategories());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", foodItems.getTotalPages());
        model.addAttribute("searchName", name);
        model.addAttribute("selectedCategoryId", categoryId);

        return "food-items/list";
    }

    // Hiển thị form thêm thực phẩm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("categories", foodItemService.findAllCategories());
        return "food-items/add";
    }

    // Xử lý thêm thực phẩm mới
    @PostMapping("/add")
    public String addFoodItem(@ModelAttribute @Valid FoodItem foodItem,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", foodItemService.findAllCategories());
            return "food-items/add";
        }

        foodItemService.save(foodItem);
        return "redirect:/food-items";
    }

    // Hiển thị form cập nhật thực phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<FoodItem> foodItem = foodItemService.findById(id);
        if (foodItem.isPresent()) {
            model.addAttribute("foodItem", foodItem.get());
            model.addAttribute("categories", foodItemService.findAllCategories());
            return "food-items/edit";
        }
        return "redirect:/food-items";
    }

    // Xử lý cập nhật thực phẩm
    @PostMapping("/edit/{id}")
    public String updateFoodItem(@PathVariable Long id,
                                 @ModelAttribute @Valid FoodItem foodItem,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", foodItemService.findAllCategories());
            return "food-items/edit";
        }

        foodItem.setId(id);
        foodItemService.save(foodItem);
        return "redirect:/food-items";
    }

    // Xóa thực phẩm
    @PostMapping("/delete/{id}")
    public String deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteById(id);
        return "redirect:/food-items";
    }
}

