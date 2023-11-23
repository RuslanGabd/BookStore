package com.ruslan.database.DAO;

import com.ruslan.entity.book.Book;

public class BookRepository extends RepositoryBase<Integer, Book> {
    public BookRepository() {
        super(Book.class);
    }


}
