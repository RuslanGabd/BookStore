package com.ruslan.database.DAO;

import com.ruslan.entity.order.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOrderDao {

    Optional<Order> findById(int id);

    Order saveOrder(Order order);

    List<Order> getOrdersList();

    void update(Order order);

    Boolean removeById(int id) throws SQLException;

    List<Order> getCompletedOrdersForPeriod(LocalDate date1, LocalDate date2);

}
