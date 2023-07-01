package com.ruslan.data.request;

import com.ruslan.data.book.Book;

import java.util.Date;

public class Request {
    private Book book;
    private int id;

    private Date date;

    public Request(Book book) {

        this.book = book;
        this.id = new RequestCounted().getId();
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Request:" +
                " id=" + id +
                ", bookId=" + book.getId() +
                ", date=" + date;
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
