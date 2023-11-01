package com.ruslan.DAO;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IBookDao {
    Optional<Book> findById(int id);

    Book saveBook(Book book);

    void update(Book book);

    Boolean removeById(int idBook);

    List<Book> getBooksList();


}
