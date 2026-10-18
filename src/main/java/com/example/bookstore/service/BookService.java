package com.example.bookstore.service;

import com.example.bookstore.dto.ManualBookMapper;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.exception.DuplicateIsbnException;
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

    public BookResponse findBookById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toResponse).orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookResponse addBooks(CreateBookRequest request){
        if(bookRepository.existsByIsbn(request.isbn())){
            throw new DuplicateIsbnException(request.isbn());
        }
        Book saved = bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toResponse(saved);   // saved now has its generated id
    }

    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookMapper.updateEntity(existing, request);   // id and isbn stay as they are
        return bookMapper.toResponse(bookRepository.save(existing));
    }

    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
