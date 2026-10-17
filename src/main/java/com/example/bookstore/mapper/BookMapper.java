package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CreateBookRequest;
import com.example.bookstore.dto.UpdateBookRequest;
import com.example.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper {

    BookResponse toResponse(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(CreateBookRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isbn", ignore = true)
    void updateEntity(@MappingTarget Book book, UpdateBookRequest request);
}