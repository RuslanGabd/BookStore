package com.ruslan.data.order;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.request.Request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private Integer id;
    private List<Book> listBook;

    private String buyer;
    private String address;
    private OrderStatus status;


    private Integer totalPrice;
    private LocalDate dateExecution;
    private LocalDate dateCreated;

    public Order() {
    }

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

