package com.ruslan.request;

import com.ruslan.book.Book;

import java.util.Date;

public class Request {
    Book book;
    int id;
    Date date;

    public Request(Book book) {
        this.book = book;
        this.id = new RequestCounted().getId();
        this.date = new Date();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
