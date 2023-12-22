package com.ruslan.services.sinterface;

import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;

import java.time.LocalDate;

public interface IBookService {


    Book createBook(String title, String author, int price, LocalDate datePublication);

    void changeStatusBook(int id, BookStatus status);

    void removeBookFromStock(int id);


}
