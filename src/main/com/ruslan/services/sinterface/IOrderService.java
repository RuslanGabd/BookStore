package com.ruslan.services.sinterface;


import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    void printList(String header, List<Order> list);

    Order createOrder(List<Book> listBooks);


    void removeOrder(int id);

}
