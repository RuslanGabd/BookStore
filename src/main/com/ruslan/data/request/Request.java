package com.ruslan.data.request;

import com.ruslan.data.book.Book;

import java.time.LocalDate;

public class Request {
    private Book book;
    private int id;

    private LocalDate date;

    public Request(Book book) {
        this.book = book;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}