package com.ruslan.database.DAO;

import com.ruslan.entity.book.Book;
import org.springframework.stereotype.Component;

@Component
public class BookRepository extends RepositoryBase<Integer, Book>  {
    public BookRepository() {
        super(Book.class);
    }

}
