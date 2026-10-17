package com.example.bookstore.controller;

import com.example.bookstore.dto.ApiErrorResponse;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getOneBook(@Positive @PathVariable Long id){
        Optional<BookResponse> book = bookService.findBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "BOOK_NOT_FOUND",
                "Book with id " + id + " does not exist",
                "/books/"+id,
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addNewBook(@Valid @RequestBody CreateBookRequest request){
        BookResponse saved = bookService.addBooks(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@Positive @PathVariable Long id,@Valid @RequestBody UpdateBookRequest request){
        return bookService.updateBook(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@Positive @PathVariable Long id){
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "BOOK_NOT_FOUND",
                "Book with id " + id + " does not exist",
                "/books/"+id,
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
