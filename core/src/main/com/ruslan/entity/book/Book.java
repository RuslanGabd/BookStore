package com.ruslan.entity.book;


import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.request.Request;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"listOrder", "listRequests"}, callSuper = true)
@Setter
@EqualsAndHashCode(callSuper = false)
public class Book extends BaseEntity<Integer> implements Serializable {

    private String title;
    private String author;
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('IN_STOCK', 'NOT_AVAILABLE', 'OUT_OF_STOCK')")
    private BookStatus status;
    private String description;

    private LocalDate datePublication;

    @ManyToMany
    @JoinTable(name = "booksorder",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "OrderID"))
    private List<Order> listOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Request> listRequests;

    public Book(String title, String author, Integer price, LocalDate datePublication, BookStatus status, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.datePublication = datePublication;
        this.status = status;
        this.description = description;
    }
}


