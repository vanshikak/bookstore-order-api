package com.example.bookstore.dto;

import java.math.BigDecimal;

public record CreateBookRequest(String title, String author, String isbn, BigDecimal price,  Integer quantity) {
}
