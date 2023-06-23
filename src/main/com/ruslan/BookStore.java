package com.ruslan;

import com.ruslan.book.Book;
import com.ruslan.book.BookStatus;
import com.ruslan.order.Order;
import com.ruslan.order.OrderStatus;
import com.ruslan.request.Request;
import com.sun.source.tree.Tree;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookStore {

    protected ArrayList<Book> stock = new ArrayList<>();
    protected ArrayList<Order> orders = new ArrayList<>();
    protected Map<Request, Book> requests = new HashMap<>();





    public BookStore() {
    }

    public Book createBook(String title, String author, int price) {
        Book bk = new Book(title, author, price);
        stock.add(bk);
        System.out.println("Created book: " + bk.toString());
        return bk;
    }

    public void changeStatusBook(Book book, BookStatus status) {
        for (Book bk : stock) {
            if (bk.equals(book)) {
                bk.setStatus(status);
                System.out.println("Book id=" + book.getId() + " changed status " + status);
            }
        }
    }

    public void addBookToStockAndCancelRequests(Book book) {
        for (Book bk : stock) {
            if (book.equals(bk)) {
                bk.setStatus(BookStatus.IN_STOCK);
                cancelRequestsOfBook(book);
                System.out.println("Book " + book.getId() + " add to stock");
            }
        }
    }


    public void removeBookFromStock(Book book) {
        for (Book bk : stock) {
            if (book.equals(bk)) {
                bk.setStatus(BookStatus.OUT_OF_STOCK);
                System.out.println("Book id=" + book.getId() + " status changed OUT_OF_STOCK");
            }
        }
    }

    public void createOrder(Book book) {
        Order ord = new Order(book);
        orders.add(ord);
        //System.out.println(orders.size());
        System.out.println("Order created with id=" + ord.getId());
        if (book.getStatus() == BookStatus.OUT_OF_STOCK) {
            createRequest(ord.getBook());
        }


    }

    public void changeOrderStatus(int id, OrderStatus status) {
        for (Order ord : orders) {
            if (ord.getId() == id) {
                if (status == OrderStatus.FULFILLED && requests.containsValue(ord.getBook())) {
                    System.out.println("Request for book id=" + ord.getBook().getId() +
                            " is not finished. Please close request");
                } else if (status == OrderStatus.FULFILLED) {
                    ord.setDateExecution(LocalDate.now());
                }
                 ord.setStatus(status);
            }
        }
    }

    public void cancelOrder(int id) {
        for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext(); )
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        System.out.println("Order with id=" + id + " was canceled");
    }

    public void cancelRequestsOfBook(Book book) {
        HashMap<Request, Book> copy = new HashMap<>(requests);
        for (Map.Entry<Request, Book> req : copy.entrySet()) {
            if (req.getValue().equals(book)) {
                requests.remove(req.getKey());
                System.out.println("All requests for book id=" + book.getId() + " deleted");
            }
        }
    }

    public void createRequest(Book book) {
        Request request = new Request(book);
        requests.put(request, book);
        System.out.print("New request created with id=" + request.getId());
        System.out.println(" Total requests: " + requests.size());
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Map<Request, Book> getRequests() {
        return requests;
    }

    public void setRequests(Map<Request, Book> requests) {
        this.requests = requests;
    }


}
