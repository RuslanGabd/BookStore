package com.ruslan.ui;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;
import com.ruslan.services.OrderService;
import com.ruslan.services.RequestService;

public interface IAction {
    final BookRepository bookRepository = BookRepository.getInstance();
    final OrderRepository orderRepository = OrderRepository.getInstance();
    final RequestRepository requestRepository = RequestRepository.getInstance();
    BookService bookService = new BookService(bookRepository, orderRepository, requestRepository);
    OrderService orderService = new OrderService(orderRepository, requestRepository, bookRepository);
    RequestService requestService = new RequestService(requestRepository, bookRepository);

    void execute();



}
