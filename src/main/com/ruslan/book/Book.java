package com.ruslan.book;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Book {

    private int id;
    private BookStatus status;
    private String title;
    private String author;
    private int price;


    private LocalDate datePublication;


    public Book(String title, String author, int price) {
        this.id = BookCounted.generateNewId();
        this.title = title;
        this.author = author;
        this.price = price;
        this.status = BookStatus.IN_STOCK;
        this.datePublication = LocalDate.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getId() == book.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {

        return "id=" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price + '\'' +
                ", date=" + datePublication + '\'' +
                ", status=" + status + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }


    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
}


