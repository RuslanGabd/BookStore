package com.ruslan.entity.order;

import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;
import com.ruslan.entity.request.Request;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable, BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "listOrder")
    private List<Book> listBook;

    private String buyer;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition="ENUM('NEW', 'COMPLETED', 'CANCELLED')")
    private OrderStatus status;
    @Column(name="total_price")

    private Integer totalPrice;
    @Column(name="date_execution")
    private LocalDate dateExecution;

    @Column(name="date_created")
    private LocalDate dateCreated;
//    @Builder.Default
//    @OneToMany(mappedBy = "order")
//    private Set<BooksOrders> booksOrders = new HashSet<>();
//

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
        this.id = id;
        this.buyer = buyer;
        this.address = address;
        this.totalPrice = totalPrice;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateExecution = dateExecution;
        this.listBook = listBooks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order:" +
                " id=" + id +
                ", buyer=" + buyer +
                ", address=" + address +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", dateCreated=" + dateCreated +
                ", dateExecution=" + dateExecution;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }


    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(LocalDate dateExecution) {
        this.dateExecution = dateExecution;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}

