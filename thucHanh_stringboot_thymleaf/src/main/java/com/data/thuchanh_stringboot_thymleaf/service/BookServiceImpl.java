package com.data.thuchanh_stringboot_thymleaf.service;

import com.data.thuchanh_stringboot_thymleaf.entity.Book;
import com.data.thuchanh_stringboot_thymleaf.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> findBookByBookName(String bookName) {
        return bookRepository.findByNameContainingIgnoreCase(bookName);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        try{
            return bookRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBookById(Long id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
