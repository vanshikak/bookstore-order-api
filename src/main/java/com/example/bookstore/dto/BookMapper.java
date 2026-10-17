package com.example.bookstore.dto;

import com.example.bookstore.model.Book;

public class BookMapper {
    public static BookResponse toResponse(Book book){
        if(book==null){
            return null;
        }
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn(),book.getQuantity() );
    }
    public static Book toEntity(CreateBookRequest request){
        if(request==null){
            return null;
        }
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPrice(request.price());
        book.setQuantity(request.quantity());
        return book;
    }
    public static void updateEntity(Book book, UpdateBookRequest request){
            if (book == null || request == null) {
                return;
            }
            // id and isbn are untouched, so they can't be changed through PUT
            book.setTitle(request.title());
            book.setAuthor(request.author());
            book.setPrice(request.price());
            book.setQuantity(request.quantity());
    }
}
