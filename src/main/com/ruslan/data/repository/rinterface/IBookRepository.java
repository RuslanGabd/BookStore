package com.ruslan.data.repository.rinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookCounted;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBookRepository {
    Book getById(int id);

    void saveBook(Book book);

    void updateStatus(int id, BookStatus status);

    void removeById(int idBook);

    List<Book> getBooksList();

    void addBook(int idBook,Book book);

}
