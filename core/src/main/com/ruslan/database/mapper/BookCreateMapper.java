package com.ruslan.database.mapper;

import com.ruslan.entity.book.Book;

public class BookCreateMapper implements Mapper<BookCreateMapper, Book> {
    @Override
    public Book mapFrom(BookCreateMapper object) {
        return
                Book.builder().build();
    }
}
