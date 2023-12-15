package com.ruslan.entity.book;


import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.request.Request;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "bookstore")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Book extends BaseEntity<Integer> implements Serializable {

    private String title;
    private String author;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private String description;

    private LocalDate datePublication;

    @ManyToMany
    @JoinTable(name = "booksorder",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "OrderID"))
    @ToString.Exclude
    private List<Order> listOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @ToString.Exclude
    private List<Request> listRequests;

    public Book(String title, String author, Integer price, LocalDate datePublication, BookStatus status, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.datePublication = datePublication;
        this.status = status;
        this.description = description;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


