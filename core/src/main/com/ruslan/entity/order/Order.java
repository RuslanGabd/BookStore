package com.ruslan.entity.order;

import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;
import com.ruslan.entity.request.Request;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "`order`", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true, exclude = {"listBook"})
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity<Integer> {

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "listOrder")
    private List<Book> listBook;

    private String buyer;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('NEW', 'COMPLETED', 'CANCELLED')")
    private OrderStatus status;
    private Integer totalPrice;
    private LocalDate dateExecution;

    private LocalDate dateCreated;


    public Order(List<Book> listBook) {
        int commonPrice = 0;
        this.listBook = listBook;
        this.status = OrderStatus.NEW;
        for (Book bk : listBook) {
            if (bk.getStatus() == BookStatus.NOT_AVAILABLE) {
                new Request(bk);
            }
            commonPrice = commonPrice + bk.getPrice();
        }
        this.totalPrice = commonPrice;
        for (Book bk : listBook)
            if (bk.getStatus() == BookStatus.NOT_AVAILABLE) {
                new Request(bk);
            }
        this.dateCreated = LocalDate.now();
    }

    public Order(int id, String buyer, String address, OrderStatus status, int totalPrice, LocalDate dateCreated,
                 LocalDate dateExecution, List<Book> listBooks) {
        this.setId(id);
        this.buyer = buyer;
        this.address = address;
        this.totalPrice = totalPrice;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateExecution = dateExecution;
        this.listBook = listBooks;
    }
}

