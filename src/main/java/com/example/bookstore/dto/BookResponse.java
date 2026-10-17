package com.example.bookstore.dto;

import java.math.BigDecimal;

public record BookResponse(Long id, String title, String author, BigDecimal price, String isbn,Integer quantity) {
}
