package com.ruslan.data.repository;

import com.ruslan.data.book.Book;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.request.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<Book> stock = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();

    // private Map<Request, Book> requests = new HashMap<>();
    public List<Book> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Book> stock) {
        this.stock = stock;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void addToStock(Book book) {
        stock.add(book);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void changeStatusOrder(int id, OrderStatus status) {
        findOrderUseId(id).setStatus(status);
        System.out.println(findOrderUseId(id).getDateExecution());
    }


    public void addDateExecutionToOrder(int id, LocalDate date) {
        findOrderUseId(id).setDateExecution(date);
    }

    public void removeRequest(int id) {
        int i = 0;
        for (Request req : requests) {
            if (req.getId() == id)
                requests.remove(i);
            i++;
        }
    }

    public Order findOrderUseId(int id) {
        for (Order ord : orders)
            if (ord.getId() == id)
                return ord;
        return null;
    }
}
