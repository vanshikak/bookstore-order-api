package com.example.bookstore.dto;

import java.math.BigDecimal;

public record UpdateBookRequest(String title, String author, BigDecimal price, Integer quantity) {
}
