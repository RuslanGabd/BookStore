package com.ruslan.services.sinterface;


import com.ruslan.data.book.Book;
import com.ruslan.data.order.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {

    Order createOrder(List<Book> listBooks);


    void removeOrder(int id) throws SQLException;


}
