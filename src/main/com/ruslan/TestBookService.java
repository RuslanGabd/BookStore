package com.ruslan;


import com.ruslan.data.book.Book;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;
import com.ruslan.services.OrderService;
import com.ruslan.services.RequestService;
import com.ruslan.ui.MenuController;

import java.time.LocalDate;
import java.util.Arrays;

public class TestBookService {

    public static void main(String[] args) {

        final BookRepository bookRepository = BookRepository.getInstance();
        final OrderRepository orderRepository = OrderRepository.getInstance();
        final RequestRepository requestRepository = RequestRepository.getInstance();
        BookService bookService = new BookService(bookRepository, orderRepository, requestRepository);
        OrderService orderService = new OrderService(orderRepository, requestRepository, bookRepository);
        RequestService requestService = new RequestService(requestRepository, bookRepository);
        RandomDate randomDate = new RandomDate();


        Book book1 = bookService.createBook("Finalist", "Teodor D", 100, randomDate.generateDateForTest());
        Book book2 = bookService.createBook("Silver", "Jack London", 50, randomDate.generateDateForTest());
        Book book3 = bookService.createBook("Chemistry", "Mohjan P.", 180, randomDate.generateDateForTest());
        Book book4 = bookService.createBook("King", "Sven Richi", 90, randomDate.generateDateForTest());
        Book book5 = bookService.createBook("Wolf", "Tramp K.", 10, randomDate.generateDateForTest());
        Book book6 = bookService.createBook("Fox", "Brus Li", 146, randomDate.generateDateForTest());
        Book book7 = bookService.createBook("Cat", "Uma Turman", 123, randomDate.generateDateForTest());


        orderService.createOrder(Arrays.asList(book1, book2));
        orderService.createOrder(Arrays.asList(book3, book4));
        orderService.createOrder(Arrays.asList(book4, book5, book6));
        orderService.createOrder(Arrays.asList(book4, book5, book6));
        orderService.createOrder(Arrays.asList(book1, book5, book6));
        orderService.createOrder(Arrays.asList(book3, book5, book6));

        orderService.changeOrderStatus(1, OrderStatus.FULFILLED);
        orderService.changeOrderStatus(3, OrderStatus.FULFILLED);
        orderService.changeOrderStatus(4, OrderStatus.FULFILLED);
        orderService.changeOrderStatus(5, OrderStatus.FULFILLED);
        orderService.changeOrderStatus(2, OrderStatus.CANCELLED);

        orderService.changeOrderDateCreated(1, randomDate.generateDateForTest());
        orderService.changeOrderDateCreated(2, randomDate.generateDateForTest());
        orderService.changeOrderDateCreated(3, randomDate.generateDateForTest());
        orderService.changeOrderDateCreated(5, randomDate.generateDateForTest());
        orderService.changeOrderDateCreated(2, randomDate.generateDateForTest());

        orderService.changeOrderDateExecution(1, randomDate.generateDateExecutionForOrder());
        orderService.changeOrderDateExecution(3, randomDate.generateDateExecutionForOrder());
        orderService.changeOrderDateExecution(4, randomDate.generateDateExecutionForOrder());
        orderService.changeOrderDateExecution(5, randomDate.generateDateExecutionForOrder());

        requestService.createRequest(book1);
        requestService.createRequest(book5);


        MenuController menuController = new MenuController();
        menuController.run();

//
//        //List of books (sort alphabetically, by date of publication, by price, by stock availability);
//        bookService.printList("Books sorted by Title Alphabetically:", bookService.getBooksSortedByTitleAlphabetically());
//        bookService.printList("Books sorted by DAte Publication:", bookService.getBooksSortedByDatePublication());
//        bookService.printList("Books sorted by Status:", bookService.getBooksSortedByStatus());
//
//
//        //List of orders (sort by date of execution, by price, by status);
//        orderService.printList("Orders sorted by Price:", orderService.getOrdersSortedByPrice());
//        orderService.printList("Orders sorted by Status:", orderService.getOrdersSortedByStatus());
//        orderService.printList("Orders sorted by DateExecution:", orderService.getOrdersSortedByDateExecution());
//
//        //List of book requests (sort by number of requests, alphabetically);
//        requestService.printList("Requests sorted by Number:", requestService.getRequestSortedByNumber());
//        requestService.printMap("Requests sorted by Alphabetically", requestService.getRequestSortedByAlphabetically());
//
//
//        //List of completed orders for a period of time (sort by date, by price);
//        orderService.printList("Orders for period sorted by Date", orderService.getOrdersSortedByDateForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//        orderService.printList("Orders for period sorted by Price", orderService.getOrdersSortedByPriceForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//
//        // The amount of money earned over a period of time;
//        System.out.println("Earned money for period: "
//                + orderService.getEarnedMoneyForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//
//
//        //The number of completed orders over a period of time;
//        System.out.println("The number of completed orders over a period of time: "
//                + orderService.getNumberFulfilledOrdersForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//
//        //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);
//        bookService.printList("Stale books sorted by Price", bookService.getStaleBooksSortedByPrice());
//        bookService.printList("Stale books sorted by Date", bookService.getStaleBooksSortedByDate());
//
//        //Order details (any customer data + books);
//        orderService.printOrderDetails(5);
//        //Description of the book.
//        bookService.printDescriptionOfBook(book7);
    }
}
