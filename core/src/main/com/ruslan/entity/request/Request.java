package com.ruslan.entity.request;

import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "request", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"book"})
@Setter
public class Request implements Serializable, BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
//    public void setBook(Book book) {
//        book.setRequest(this);
//        this.book = book;}

    public Request(Book book) {
        this.book = book;
        this.date = LocalDate.now();
    }

    public Request(int id, Book book, LocalDate localDate) {
        this.id = id;
        this.book = book;
        this.date = localDate;
    }
}
