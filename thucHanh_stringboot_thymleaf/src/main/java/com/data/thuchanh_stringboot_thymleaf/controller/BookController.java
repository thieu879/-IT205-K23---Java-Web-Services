package com.data.thuchanh_stringboot_thymleaf.controller;

import com.data.thuchanh_stringboot_thymleaf.entity.Book;
import com.data.thuchanh_stringboot_thymleaf.entity.CategoryBook;
import com.data.thuchanh_stringboot_thymleaf.service.BookService;
import com.data.thuchanh_stringboot_thymleaf.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryBookService categoryBookService;

    public BookController(BookService bookService, CategoryBookService categoryBookService) {
        this.bookService = bookService;
        this.categoryBookService = categoryBookService;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryBookService.findAllCategoryBooks());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        if (bookService.saveBook(book)) {
            redirectAttributes.addFlashAttribute("success", "Thêm sách thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm sách!");
        }
        return "redirect:/books";
    }

    @GetMapping("/detail/{id}")
    public String getBookDetail(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "books/detail";
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryBookService.findAllCategoryBooks());
            return "books/edit";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        book.setId(id);
        if (bookService.updateBook(book)) {
            redirectAttributes.addFlashAttribute("success", "Cập nhật sách thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật sách!");
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (bookService.deleteBookById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa sách thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa sách!");
        }
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
        List<Book> books;
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookService.findBookByBookName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            books = bookService.findAllBooks();
        }
        model.addAttribute("books", books);
        return "books/list";
    }
}
