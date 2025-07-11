package com.data.thuchanh_stringboot_thymleaf.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String publisher;
    private String yearPublish;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_book_id")
    private CategoryBook categoryBook;

    // Constructors
    public Book() {}

    public Book(String name, String author, String publisher, String yearPublish, double price, CategoryBook categoryBook) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.yearPublish = yearPublish;
        this.price = price;
        this.categoryBook = categoryBook;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getYearPublish() { return yearPublish; }
    public void setYearPublish(String yearPublish) { this.yearPublish = yearPublish; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public CategoryBook getCategoryBook() { return categoryBook; }
    public void setCategoryBook(CategoryBook categoryBook) { this.categoryBook = categoryBook; }

}
