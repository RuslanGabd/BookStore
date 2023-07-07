package com.ruslan.services.sinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {


    void printList(String header, List<Book> list);

    Book createBook(String title, String author, int price, LocalDate datePublication);

    void changeStatusBook(int id, BookStatus status);

    void removeBookFromStock(int id);


}
