package com.example.bookstore.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateBookRequest(@NotBlank @Size(max = 50) String title,
                                @NotBlank @Size(max = 50) String author,
                                @NotNull @Positive BigDecimal price,
                                @NotNull @PositiveOrZero Integer quantity) {}
