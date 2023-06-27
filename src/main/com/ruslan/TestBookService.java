package com.ruslan;


import com.ruslan.data.book.Book;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.Repository;
import com.ruslan.services.BookService;

import java.util.Arrays;

public class TestBookService {
    public static void main(String[] args) {

        final Repository repository = new Repository();
        BookService bookService = new BookService(repository);

        Book book1 = bookService.createBook("Finalist", "Teodor D", 100);
        Book book2 = bookService.createBook("Silver", "Jack London", 50);
        Book book3 = bookService.createBook("Chemistry", "Mohjan P.", 80);
        Book book4 = bookService.createBook("King", "Sven Richi", 90);
        Book book5 = bookService.createBook("Wolf", "Tramp K.", 10);
        Book book6 = bookService.createBook("Fox", "Brus Li", 146);

//        // -Write off the book from stock (set to "out of stock" status);
//        bookService.changeStatusBook(book1, BookStatus.OUT_OF_STOCK);
//        bookService.changeStatusBook(book2, BookStatus.NOT_AVAILABLE);
//        bookService.changeStatusBook(book3, BookStatus.OUT_OF_STOCK);
//        bookService.changeStatusBook(book4, BookStatus.OUT_OF_STOCK);
//        bookService.changeStatusBook(book5, BookStatus.NOT_AVAILABLE);
//        bookService.changeStatusBook(book6, BookStatus.NOT_AVAILABLE);
//

//        // -Create an order;
        bookService.createOrder(Arrays.asList(book1, book2));
        bookService.createOrder(Arrays.asList(book3, book4));
        bookService.createOrder(Arrays.asList(book4, book5, book6));
        bookService.createOrder(Arrays.asList(book4, book5, book6));
//        //-Change order status (new, fulfilled, cancelled);
        //    bookService.printAllOrdersSortedByPrice();
        bookService.changeOrderStatus(1, OrderStatus.FULFILLED);

        bookService.changeOrderStatus(3, OrderStatus.FULFILLED);
        bookService.changeOrderStatus(2, OrderStatus.CANCELLED);

//        bookService.changeOrderStatus(2, OrderStatus.FULFILLED);
//        //-Cancel an order;
//        bookService.cancelOrder(1);
//        //-Add a book to stock (closes all book requests and changes its status into "in stock");
//        bookService.addBookToStockAndCancelRequests(book2);
//        bookService.createMapAllBookSortedTitle();
//        System.out.println(bookService.getBookSortedTitle().tailMap(bookService.getBookSortedTitle().firstKey()));
//        System.out.println(bookService.getBookSortedTitle().firstKey());
//        bookService.printAllBookSortedTitle();

        bookService.createRequest(book1);
        bookService.createRequest(book5);
        bookService.printAllOrdersSortedByStatus();
        bookService.printAllOrdersSortedByDateExecution();
        //   bookServiceSorted.printAllBooksSortedByStatus();
        bookService.printAllRequestSortedByNumber();
        bookService.printAllOrdersSortedByStatus();
        // bookService.createTreeMapOrders();
        //  bookService.printAllOrdersSortedByExecution();
        //-Leave a request for a book.
//        bookService.createRequest(book3);
//        bookService.cancelRequestsOfBook(book3);
//        //The order cannot be completed until the book request is fulfilled.
//        bookService.changeStatusBook(book2, BookStatus.OUT_OF_STOCK);
//        bookService.createOrder(book2);
//        bookService.changeOrderStatus(3, OrderStatus.FULFILLED);
        bookService.printAllRequestSortedByAlphabetically();
    }
}
