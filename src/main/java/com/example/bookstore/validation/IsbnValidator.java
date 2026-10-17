package com.example.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<ValidIsbn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 1. null → let @NotBlank decide whether ISBN is required
        if (value == null) {
            return true;
        }

        // 2. normalise: allow "978-0-13-468599-1" and "978 0 13 468599 1"
        String isbn = value.replace("-", "").replace(" ", "");

        // 3. exactly 13 digits, nothing else
        if (!isbn.matches("\\d{13}")) {
            return false;
        }

        // 4. checksum: first 12 digits weighted 1,3,1,3,...
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';          // '7' - '0' = 7
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int expectedCheckDigit = (10 - sum % 10) % 10;
        int actualCheckDigit = isbn.charAt(12) - '0';

        return expectedCheckDigit == actualCheckDigit;
    }
}