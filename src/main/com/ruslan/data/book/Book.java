package com.ruslan.data.book;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private int id;
    private BookStatus status;
    private String title;
    private String author;
    private int price;
    private LocalDate datePublication;

    private LocalDate dataOfReceipt;

    private String description;


    public Book(String title, String author, int price, LocalDate datePublication) {
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
        return "Book:" +
                " id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", price=" + price +
                ", date=" + datePublication +
                ", status=" + status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


