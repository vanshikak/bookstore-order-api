package com.example.bookstore.controller;


import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookResponse> getAllBooks(){
        return bookService.allBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getOneBook(@Positive @PathVariable Long id){
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addNewBook(@Valid @RequestBody CreateBookRequest request){
        BookResponse saved = bookService.addBooks(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@Positive @PathVariable Long id,@Valid @RequestBody UpdateBookRequest request){
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@Positive @PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
