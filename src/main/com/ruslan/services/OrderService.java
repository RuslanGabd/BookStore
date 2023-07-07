package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public void changeOrderDateCreated(int id, LocalDate date) {
        orderRepository.getById(id).setDateCreated(date);
    }
    public void changeOrderDateExecution(int id, LocalDate date) {
        orderRepository.getById(id).setDateExecution(date);
    }


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

    public List<Order> getListOrdersFulfilled(List<Order> list) {
        List<Order> listFulfilledOrders = new ArrayList<>();
        for (Order ord : list) {
            if (ord.getStatus() == OrderStatus.FULFILLED) {
                listFulfilledOrders.add(ord);
            }
        }
        return listFulfilledOrders;
    }

    public void changeOrderStatus(int id, OrderStatus status) {
        for (Order ord : orderRepository.getOrdersList()) {
            if (ord.getId() != id) {
                continue;
            }
            if (status == OrderStatus.FULFILLED) {
                if (requestRepository.getRequestList().size() != 0) { //search request for book
                    for (Request rq : requestRepository.getRequestList()) {
                        if (ord.getListBook().contains(rq.getBook())) {
                            System.out.println("Request for book id= " + rq.getBook().getId() +
                                    " is not finished. Please close request");
                        }
                    }
                } else {
                    orderRepository.updateStatus(id, status);
                    orderRepository.setDateExecution(id, LocalDate.now());
                    System.out.println("Status for Order with id=" + id + " changed: " + status);
                }
            } else {
                orderRepository.updateStatus(id, status);
                System.out.println("Status for Order with id=" + id + " changed: " + status);
            }
        }
    }

    @Override
    public void removeOrder(int id) {
        orderRepository.removeById(id);
        System.out.println("Order with id=" + id + " was canceled");
    }

    //Order details (any customer data + books);
    public void printOrderDetails(int id) {
        for (Order ord : orderRepository.getOrdersList())
            if (ord.getId() == id) {
                System.out.println("Customer: " + ord.getBuyer());
                System.out.println("Books: ");
                ord.getListBook().forEach(book -> System.out.println(book.toString()));
            }
    }

    // The number of completed orders over a period of time;
    public Integer getCountFulfilledOrdersForPeriod(LocalDate date1, LocalDate date2) {
        int countOrders = 0;
        List<Order> orderListEarnedMoney = getOrderListForPeriodByDate(orderRepository.getOrdersList(), date1, date2);
        for (Order ord : orderListEarnedMoney)
            if (ord.getStatus() == OrderStatus.FULFILLED)
                countOrders++;
        return countOrders;
    }

    // The amount of money earned over a period of time;
    public Integer getEarnedMoneyForPeriod(LocalDate date1, LocalDate date2) {
        return orderRepository.getOrdersList()
                .stream()
                .filter(order ->
                        order.getStatus() == OrderStatus.FULFILLED)
                .filter(order ->
                        order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .map(Order::getTotalPrice)
                .reduce(0, Integer::sum);

    }

    // List of completed orders for a period of time (sort by date, by price);
    public List<Order> orderListForPeriodByExecutionDate(List<Order> orderList, LocalDate date1, LocalDate date2) {
        return orderList
                .stream()
                .filter(order ->
                        order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .collect(Collectors.toList());
    }

    public List<Order> getOrderListForPeriodByDate(List<Order> orderList, LocalDate date1, LocalDate date2) {
        return orderList
                .stream()
                .filter(order ->
                        (order.getDateCreated().isAfter(date1)
                                || order.getDateCreated().isEqual(date1))
                                && (order.getDateCreated().isBefore(date2))
                                || order.getDateCreated().isEqual(date2))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersSortedByDateForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = getOrderListForPeriodByDate(orderRepository.getOrdersList(), date1, date2);
        orderList.sort(Comparator.comparing(Order::getDateCreated));
        return orderList;
    }

    public List<Order> getOrdersSortedByPriceForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = getOrderListForPeriodByDate(orderRepository.getOrdersList(), date1, date2);
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }

    public List<Order> getOrdersSortedByStatus() {
        List<Order> sortedOrders = orderRepository.getOrdersList();
        Order temp;
        for (Order ord : orderRepository.getOrdersList()) {
            for (int i = 0; i < sortedOrders.size() - 1; i++) {
                if (sortedOrders.get(i).getStatus() == OrderStatus.FULFILLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.NEW) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
                if (sortedOrders.get(i).getStatus() == OrderStatus.CANCELLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.NEW) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
                if (sortedOrders.get(i).getStatus() == OrderStatus.CANCELLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.FULFILLED) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
            }
        }
        return sortedOrders;
    }


    @Override
    public void printList(String header, List<Order> list) {
        System.out.println(header);
        for (Order ord : list) {
            System.out.println(ord.toString() + "; ");
        }
        System.out.println();
    }
}
