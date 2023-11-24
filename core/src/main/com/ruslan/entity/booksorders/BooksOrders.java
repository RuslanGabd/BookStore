package com.ruslan.entity.booksorders;

import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Table(name = "booksorder", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class BooksOrders extends BaseEntity<Integer> {

    @ManyToOne
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Instant createdAt;


}
