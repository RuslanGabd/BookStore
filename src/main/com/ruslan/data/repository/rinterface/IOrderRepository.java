package com.ruslan.data.repository.rinterface;

import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository {

    void saveOrder(Order order);


    List<Order> getOrdersList();

    Order getById(int id);

    void updateStatus(int id, OrderStatus status);

    void removeById(int id);

    void setDateExecution(int id, LocalDate date);

}
