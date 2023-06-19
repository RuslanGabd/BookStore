package com.ruslan.order;

import com.ruslan.book.Book;
import com.ruslan.book.BookStatus;
import com.ruslan.request.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order {
    Integer id;
    Book book;
    Date dateOrder;
    String buyer;
    String address;

    OrderStatus status;

    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<Request> request = new ArrayList<>();

    public Order(Book book) {
        this.id = new OrderCounted().getId();
        this.book = book;
        this.status = OrderStatus.NEW;
        if (book.getStatus() == BookStatus.NOT_AVAILABLE) {
            new Request(book);
        }
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
}

