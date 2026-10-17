package com.example.bookstore.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer quantity;
    @Column(nullable=false,unique=true,length=20)
    private String isbn;

    public Book() {
    }

    public Book(Long id, String title, String author, BigDecimal price, Integer quantity, String isbn){
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.isbn = isbn;
    }

    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public String getIsbn(){
        return isbn;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
}
