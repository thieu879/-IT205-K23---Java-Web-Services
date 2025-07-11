package com.data.thuchanh_stringboot_thymleaf.service;

import com.data.thuchanh_stringboot_thymleaf.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findBookByBookName(String bookName);
    List<Book> findAllBooks();
    Book findBookById(Long id);
    boolean saveBook(Book book);
    boolean deleteBookById(Long id);
    boolean updateBook(Book book);
}
