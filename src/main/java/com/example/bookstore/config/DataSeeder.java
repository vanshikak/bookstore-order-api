package com.example.bookstore.config;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            return;   // data already exists, don't seed again
        }

        bookRepository.saveAll(List.of(
                new Book(null, "The Silent Patient", "Alex Michaelides", new BigDecimal("499"), 10, "978-1-78280-830-8"),
                new Book(null, "Too Good to be True", "Prajakta Koli", new BigDecimal("399"), 5,"978-1-78280-513-8"),
                new Book(null, "Ward D", "Freida McFadden", new BigDecimal("449"), 20,"978-1-78280-125-8")
        ));
    }
}