package com.ruslan.entity.booksorders;

import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Table(name = "booksorder", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Instant createdAt;


//    public void setOrder(Order order) {
//        this.order = order;
//        this.order.getBooksOrders().add(this);
//    }
//    public void setBook(Book book) {
//        this.book = book;
//        this.book.getBooksOrders().add(this);
//    }
}
