package com.ruslan.entity.request;

import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.book.Book;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "request", schema = "bookstore")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Request extends BaseEntity<Integer> {

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookid", nullable = false)
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Request request = (Request) o;
        return getId() != null && Objects.equals(getId(), request.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
