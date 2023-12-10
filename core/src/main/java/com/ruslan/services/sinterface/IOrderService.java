package java.com.ruslan.services.sinterface;


import java.com.ruslan.entity.book.Book;
import java.com.ruslan.entity.order.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {

    Order createOrder(List<Book> listBooks);


    void removeOrder(int id) throws SQLException;


}
