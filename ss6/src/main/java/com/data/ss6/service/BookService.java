package com.data.ss6.service;

import com.data.ss6.entity.Book;
import com.data.ss6.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public boolean deleteById(Long id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Book update(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            return null;
        }
    }
}
