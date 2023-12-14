package com.ruslan.services.sinterface;


import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {

    Order createOrder(List<Book> listBooks);


    void removeOrder(int id) throws SQLException;


}
