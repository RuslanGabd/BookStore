package com.ruslan.entity.book;


import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.request.Request;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"listOrder", "requests"})
@Setter

public class Book implements Serializable, BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition="ENUM('IN_STOCK', 'NOT_AVAILABLE', 'OUT_OF_STOCK')")
    private BookStatus status;
    private String description;


    @Column(name = "date_publication")
    private LocalDate datePublication;

    @ManyToMany
    @JoinTable(name = "booksorder",
    joinColumns = @JoinColumn(name = "BookID"),
    inverseJoinColumns = @JoinColumn(name="OrderID"))
    private List<Order> listOrder;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "book_id")

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Request> requests = new HashSet<Request>();


    public Book(String title, String author, Integer price, LocalDate datePublication, BookStatus status, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.datePublication = datePublication;
        this.status = status;
        this.description = description;
    }

    public Book(String title, String author, int price, LocalDate datePublication) {
    }


    public void setRequest(Request request) {
    }
}


