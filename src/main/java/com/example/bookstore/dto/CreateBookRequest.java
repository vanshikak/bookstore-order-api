package com.example.bookstore.dto;

import com.example.bookstore.validation.ValidIsbn;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateBookRequest(@NotBlank @Size(max = 50) String title,
                                @NotBlank @Size(max = 50) String author,
                                @NotBlank @ValidIsbn String isbn,
                                @NotNull @Positive BigDecimal price,
                                @NotNull @PositiveOrZero Integer quantity) {
}
