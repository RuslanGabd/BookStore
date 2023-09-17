package com.ruslan.ui.action;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;
import com.ruslan.services.OrderService;
import com.ruslan.services.RequestService;

public class Action {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final RequestRepository requestRepository;
    BookService bookService;
    RequestService requestService;
    OrderService orderService;

    public Action() {
        this.bookRepository = BookRepository.getInstance();
        this.orderRepository = OrderRepository.getInstance();
        this.requestRepository = RequestRepository.getInstance();
        this.bookService = new BookService(bookRepository, orderRepository, requestRepository);
        this.requestService = new RequestService(requestRepository, bookRepository);
        this.orderService = new OrderService(orderRepository, requestRepository);
    }
}
