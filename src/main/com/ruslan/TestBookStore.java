package com.ruslan;


import com.ruslan.book.Book;
import com.ruslan.book.BookStatus;
import com.ruslan.order.OrderStatus;

public class TestBookStore {
    public static void main(String[] args) {

        BookStore bookStore = new BookStore();
        Book book1 = bookStore.createBook("Finalist", "Teodor D", 100);
        Book book2 = bookStore.createBook("Silver", "Jack London", 50);
        Book book3 = bookStore.createBook("Chemistry", "Mohjan P.", 80);
        Book book4 = bookStore.createBook("King", "Sven Richi", 90);
        Book book5 = bookStore.createBook("Wolf", "Tramp K.", 10);

        // -Write off the book from stock (set to "out of stock" status);
        bookStore.changeStatusBook(book2, BookStatus.OUT_OF_STOCK);

        // -Create an order;
        bookStore.createOrder(book2);
        //-Change order status (new, fulfilled, cancelled);
        bookStore.createOrder(book1);
        bookStore.changeOrderStatus(2, OrderStatus.FULFILLED);
        //-Cancel an order;
        bookStore.cancelOrder(1);
        //-Add a book to stock (closes all book requests and changes its status into "in stock");
        bookStore.addBookToStockAndCancelRequests(book2);

        //-Leave a request for a book.
        bookStore.createRequest(book3);
        bookStore.cancelRequestsOfBook(book3);

        //The order cannot be completed until the book request is fulfilled.
        bookStore.changeStatusBook(book2, BookStatus.OUT_OF_STOCK);
        bookStore.createOrder(book2);
        bookStore.changeOrderStatus(3, OrderStatus.FULFILLED);
    }
}
