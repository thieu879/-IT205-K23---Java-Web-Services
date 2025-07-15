package com.data.ss6.controller;

import com.data.ss6.entity.Book;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<DataResponse<Iterable<Book>>> getAllBooks() {
        Iterable<Book> books = bookService.findAll();
        return ResponseEntity.ok(new DataResponse<>(books, HttpStatus.OK));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            return ResponseEntity.ok(new DataResponse<>(book, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
    @PostMapping
    public ResponseEntity<DataResponse<Book>> createBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(savedBook, HttpStatus.CREATED));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = bookService.update(book);
        if (updatedBook != null) {
            return ResponseEntity.ok(new DataResponse<>(updatedBook, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
}
