package com.data.thuchanh_stringboot_thymleaf.controller;

import com.data.thuchanh_stringboot_thymleaf.entity.CategoryBook;
import com.data.thuchanh_stringboot_thymleaf.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryBookService categoryBookService;

    public CategoryController(CategoryBookService categoryBookService) {
        this.categoryBookService = categoryBookService;
    }

    @GetMapping()
    public String getAllCategories(Model model) {
        List<CategoryBook> categories = categoryBookService.findAllCategoryBooks();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new CategoryBook());
        return "categories/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryBook category, RedirectAttributes redirectAttributes) {
        if (categoryBookService.saveCategoryBook(category)) {
            redirectAttributes.addFlashAttribute("success", "Thêm danh mục thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm danh mục!");
        }
        return "redirect:/categories";
    }

    @GetMapping("/detail/{id}")
    public String getCategoryDetail(@PathVariable Long id, Model model) {
        CategoryBook category = categoryBookService.findCategoryBookById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "categories/detail";
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryBook category = categoryBookService.findCategoryBookById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "categories/edit";
        }
        return "redirect:/categories";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryBook category, RedirectAttributes redirectAttributes) {
        category.setId(id);
        if (categoryBookService.updateCategoryBook(category)) {
            redirectAttributes.addFlashAttribute("success", "Cập nhật danh mục thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật danh mục!");
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (categoryBookService.deleteCategoryBookById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa danh mục thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa danh mục!");
        }
        return "redirect:/categories";
    }

    @GetMapping("/search")
    public String searchCategories(@RequestParam(required = false) String keyword, Model model) {
        List<CategoryBook> categories;
        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = categoryBookService.findByCategoryBookName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            categories = categoryBookService.findAllCategoryBooks();
        }
        model.addAttribute("categories", categories);
        return "categories/list";
    }
}
