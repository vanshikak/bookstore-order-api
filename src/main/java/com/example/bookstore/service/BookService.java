package com.example.bookstore.service;

import com.example.bookstore.dto.BookMapper;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> allBooks(){
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public Optional<BookResponse> findBookById(Long id){
        return bookRepository.findById(id)
                .map(BookMapper::toResponse);
    }

    public BookResponse addBooks(CreateBookRequest request){
        Book saved = bookRepository.save(BookMapper.toEntity(request));
        return BookMapper.toResponse(saved);   // saved now has its generated id
    }

    public Optional<BookResponse> updateBook(Long id, UpdateBookRequest request) {
        return bookRepository.findById(id)            // empty -> 404 in the controller
                .map(existing -> {
                    BookMapper.updateEntity(existing, request);   // id and isbn stay as they are
                    return bookRepository.save(existing);
                })
                .map(BookMapper::toResponse);
    }

    public boolean deleteBook(Long id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
