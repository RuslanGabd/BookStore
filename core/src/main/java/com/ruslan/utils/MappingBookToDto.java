package com.ruslan.utils;

import com.ruslan.dto.BookDto;
import com.ruslan.entity.book.Book;
import org.springframework.stereotype.Service;

@Service
public class MappingBookToDto {

    public BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getDescription(),
                book.getStatus(),
                book.getDatePublication());
    }

    public Book mapToBook(BookDto dto) {
        return new Book(dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice(),
                dto.getDatePublication(),
                dto.getStatus(),
                dto.getDescription());
    }
}