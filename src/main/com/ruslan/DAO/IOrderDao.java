package com.ruslan.DAO;

import com.ruslan.data.order.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOrderDao {

    Optional<Order> findById(int id);

    Order saveOrder(Order order);

    List<Order> getOrdersList();

    Optional<Order> getById(int id);

    void update(Order order);

    Boolean removeById(int id);

    void setDateExecution(int id, LocalDate date);

    void addOrder(Integer id, Order order);

    List<Order> getCompletedOrdersForPeriod(LocalDate date1, LocalDate date2);

}
