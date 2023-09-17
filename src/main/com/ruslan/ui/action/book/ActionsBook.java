package com.ruslan.ui.action.book;

import com.ruslan.config.ConfigProperties;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ActionsBook {
    static final Logger logger = LogManager.getLogger(ActionsBook.class);
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final RequestRepository requestRepository;
    BookService bookService;

    ConfigProperties configProperties = new ConfigProperties();

    public ActionsBook() {
        this.bookRepository = BookRepository.getInstance();
        this.orderRepository = OrderRepository.getInstance();
        this.requestRepository = RequestRepository.getInstance();
        this.bookService = new BookService(bookRepository, orderRepository, requestRepository);
    }

}
