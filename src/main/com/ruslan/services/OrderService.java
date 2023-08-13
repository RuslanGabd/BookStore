package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.sinterface.IOrderService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, RequestRepository requestRepository,
                        BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Order createOrder(List<Book> listBooks) {
        Order ord = new Order(listBooks);
        orderRepository.saveOrder(ord);
        System.out.println("Order created with id=" + ord.getId() + " contains " + listBooks.size() + " books");
        for (Book book : listBooks) {
            if (book.getStatus() == BookStatus.OUT_OF_STOCK) {
                requestRepository.createRequest(book);
            }
        }
        return ord;
    }

    public void changeOrderDateCreated(int orderId, LocalDate date) {
        orderRepository.getById(orderId).ifPresent(order -> order.setDateCreated(date));
    }

    public void changeOrderDateExecution(int orderId, LocalDate date) {
        orderRepository.getById(orderId).ifPresent(order -> order.setDateExecution(date));
    }

    // List of orders (sort by date of execution, by price, by status);
    public List<Order> getOrdersSortedByDateExecution() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getDateExecution,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return orderList;
    }

    public List<Order> getOrdersSortedByPrice() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }

    public List<Order> getOrdersSortedByStatus() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getStatus));
        return orderList;
    }


    public void changeOrderStatus(int orderId, OrderStatus newOrderStatus) {
        orderRepository.getById(orderId).ifPresent(order -> {
            if (newOrderStatus == OrderStatus.COMPLETED) {
                for (Book book : order.getListBook())
                    if (requestRepository.getRequestForBook(book.getId()) != null) {
                        System.out.println("Request for book with id="
                                + requestRepository.getRequestForBook(book.getId()).getBook().getId()
                                + " is not finished. Please close request");
                        break;
                    } else {
                        orderRepository.updateStatus(orderId, newOrderStatus);
                        orderRepository.setDateExecution(orderId, LocalDate.now());
                        System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
                    }
            } else {
                orderRepository.updateStatus(orderId, newOrderStatus);
                System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
            }
        });
    }

    @Override
    public void removeOrder(int orderId) {
        orderRepository.removeById(orderId);
    }

    //Order details (any customer data + books);
    public Order OrderDetails(int orderId) {
        return orderRepository.getById(orderId).orElse(null);
    }

    // The number of completed orders over a period of time;
    public int getCountCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        return orderRepository.getCompletedOrdersForPeriod(date1, date2).size();
    }

    // The amount of money earned over a period of time;
    public Integer getEarnedMoneyForPeriod(LocalDate date1, LocalDate date2) {
        return orderRepository.getCompletedOrdersForPeriod(date1, date2)
                .stream()
                .map(Order::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    // List of completed orders for a period of time (sort by date, by price);
    public List<Order> getCompletedOrdersSortedByDateForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderRepository.getCompletedOrdersForPeriod(date1, date2);
        orderList.sort(Comparator.comparing(Order::getDateCreated));
        return orderList;
    }

    public List<Order> getCompletedOrdersSortedByPriceForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderRepository.getCompletedOrdersForPeriod(date1, date2);
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }
}
