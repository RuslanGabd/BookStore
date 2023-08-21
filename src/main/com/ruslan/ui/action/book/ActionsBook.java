package com.ruslan.ui.action.book;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;

public class  ActionsBook {
    final BookRepository bookRepository;
    final OrderRepository orderRepository;
    final RequestRepository requestRepository;

    BookService bookService ;
    public ActionsBook () {
        this.bookRepository = BookRepository.getInstance();
        this.orderRepository = OrderRepository.getInstance();
        this.requestRepository = RequestRepository.getInstance();
        this.bookService=new BookService(bookRepository, orderRepository, requestRepository);
    }

}
