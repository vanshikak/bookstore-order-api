package com.example.bookstore.service;

import com.example.bookstore.dto.ManualBookMapper;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.bookstore.mapper.BookMapper;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookResponse> allBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public Optional<BookResponse> findBookById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toResponse);
    }

    public BookResponse addBooks(CreateBookRequest request){
        Book saved = bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toResponse(saved);   // saved now has its generated id
    }

    public Optional<BookResponse> updateBook(Long id, UpdateBookRequest request) {
        return bookRepository.findById(id)            // empty -> 404 in the controller
                .map(existing -> {
                    bookMapper.updateEntity(existing, request);   // id and isbn stay as they are
                    return bookRepository.save(existing);
                })
                .map(bookMapper::toResponse);
    }

    public boolean deleteBook(Long id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
