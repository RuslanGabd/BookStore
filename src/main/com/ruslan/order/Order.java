package com.ruslan.order;

import com.ruslan.book.Book;
import com.ruslan.book.BookStatus;
import com.ruslan.request.Request;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Order {
    private Integer id;
    private Book book;
    private Date dateOrder;
    private String buyer;
    private String address;
    private OrderStatus status;

    private LocalDate dateExecution;


    public Order(Book book) {
        this.id = OrderCounted.generateNewId();
        this.book = book;
        this.status = OrderStatus.NEW;
        if (book.getStatus() == BookStatus.NOT_AVAILABLE) {
            new Request(book);
        }
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
        return "Order{" +
                "id=" + id +
                ", titleBook=" + book.getTitle() +
                ", dateOrder=" + dateOrder +
                ", buyer='" + buyer + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", dateExecution=" + dateExecution +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
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
}

