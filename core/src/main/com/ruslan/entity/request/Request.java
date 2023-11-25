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
@ToString(callSuper = true, exclude = {"book"})
@Setter
@EqualsAndHashCode(callSuper = false)
public class Request extends BaseEntity<Integer> {

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;


    public Request(Book book) {
        this.book = book;
        this.date = LocalDate.now();
    }

    public Request(int id, Book book, LocalDate localDate) {
        this.setId(id);
        this.book = book;
        this.date = localDate;
    }
}
