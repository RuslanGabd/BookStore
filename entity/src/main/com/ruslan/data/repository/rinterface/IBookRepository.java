package com.ruslan.data.repository.rinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;

import java.util.List;

public interface IBookRepository {
    Book getById(int id);

    void saveBook(Book book);

    void updateStatus(int id, BookStatus status);

    void removeById(int idBook);

    List<Book> getBooksList();

    void addBook(int idBook,Book book);

}
