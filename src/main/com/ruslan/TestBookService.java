package com.ruslan;


import com.ruslan.data.book.Book;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.Repository;
import com.ruslan.services.BookService;

import java.time.LocalDate;
import java.util.Arrays;

public class TestBookService {

    public static void main(String[] args) {


        final Repository repository = new Repository();
        BookService bookService = new BookService(repository);
        RandomDate randomDate = new RandomDate();

        Book book1 = bookService.createBook("Finalist", "Teodor D", 100, randomDate.generateDateForTest());
        Book book2 = bookService.createBook("Silver", "Jack London", 50, randomDate.generateDateForTest());
        Book book3 = bookService.createBook("Chemistry", "Mohjan P.", 180, randomDate.generateDateForTest());
        Book book4 = bookService.createBook("King", "Sven Richi", 90, randomDate.generateDateForTest());
        Book book5 = bookService.createBook("Wolf", "Tramp K.", 10, randomDate.generateDateForTest());
        Book book6 = bookService.createBook("Fox", "Brus Li", 146, randomDate.generateDateForTest());
        Book book7 = bookService.createBook("Cat", "Uma Turman", 123, randomDate.generateDateForTest());

        bookService.createOrder(Arrays.asList(book1, book2));
        bookService.createOrder(Arrays.asList(book3, book4));
        bookService.createOrder(Arrays.asList(book4, book5, book6));
        bookService.createOrder(Arrays.asList(book4, book5, book6));
        bookService.createOrder(Arrays.asList(book1, book5, book6));
        bookService.createOrder(Arrays.asList(book3, book5, book6));

        bookService.changeOrderStatus(1, OrderStatus.FULFILLED);
        bookService.changeOrderStatus(3, OrderStatus.FULFILLED);
        bookService.changeOrderStatus(4, OrderStatus.FULFILLED);
        bookService.changeOrderStatus(5, OrderStatus.FULFILLED);
        bookService.changeOrderStatus(2, OrderStatus.CANCELLED);

        bookService.changeOrderDateCreated(1, randomDate.generateDateForTest());
        bookService.changeOrderDateCreated(2, randomDate.generateDateForTest());
        bookService.changeOrderDateCreated(3, randomDate.generateDateForTest());
        bookService.changeOrderDateCreated(5, randomDate.generateDateForTest());
        bookService.changeOrderDateCreated(2, randomDate.generateDateForTest());

        bookService.changeOrderDateExecution(1, randomDate.generateDateExecutionForOrder());
        bookService.changeOrderDateExecution(3, randomDate.generateDateExecutionForOrder());
        bookService.changeOrderDateExecution(4, randomDate.generateDateExecutionForOrder());
        bookService.changeOrderDateExecution(5, randomDate.generateDateExecutionForOrder());

        bookService.createRequest(book1);
        bookService.createRequest(book5);

        //List of books (sort alphabetically, by date of publication, by price, by stock availability);
        bookService.printAllBooksSortedByTitleAlphabetically();
        bookService.printAllBooksSortedByPrice();
        bookService.printAllBooksSortedByDatePublication();
        bookService.printAllBooksSortedByStatus();

        //List of orders (sort by date of execution, by price, by status);
        bookService.printAllOrdersSortedByPrice();
        bookService.printAllOrdersSortedByStatus();
        bookService.printAllOrdersSortedByDateExecution();

        //List of book requests (sort by number of requests, alphabetically);
        bookService.printAllRequestSortedByNumber();
        bookService.printAllRequestSortedByAlphabetically();

        //List of completed orders for a period of time (sort by date, by price);
        bookService.printOrdersSortedByDateForPeriod(LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 06, 30));
        bookService.printOrdersSortedByPriceForPeriod(LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 06, 30));

        // The amount of money earned over a period of time;
        bookService.printEarnedMoneyForPeriod(LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 06, 30));

        //The number of completed orders over a period of time;
        bookService.printNumberFulfilledOrdersForPeriod(LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 06, 30));

        //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);
        bookService.printStaleBooksSortedByPrice();
        bookService.printStaleBooksSortedByDate();

        //Order details (any customer data + books);
        bookService.printOrderDetails(5);
        //Description of the book.
        bookService.printDescriptionOfBook(book7);
    }
}
