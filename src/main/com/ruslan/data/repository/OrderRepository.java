package com.ruslan.data.repository;

import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderCounted;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.rinterface.IOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRepository implements IOrderRepository {

    private final Map<Integer, Order> ordersMap = new HashMap<>();


    @Override
    public void saveOrder(Order order) {
        int id = OrderCounted.generateNewId();
        order.setId(id);
        ordersMap.put(id, order);
    }

    @Override
    public List<Order> getOrdersList() {
        return new ArrayList<>(ordersMap.values());
    }

    @Override
    public Order getById(int id) {
        return ordersMap.get(id);
    }


    @Override
    public void updateStatus(int id, OrderStatus status) {
        getById(id).setStatus(status);
    }

    @Override
    public void removeById(int id) {
        ordersMap.remove(id);
    }

    @Override
    public void setDateExecution(int id, LocalDate date) {
        getById(id).setDateExecution(date);
    }

    public List<Order> getCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        return ordersMap.values().stream().filter(order ->
                        order.getStatus() == OrderStatus.COMPLETED
                                && order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersForPeriodDateExecution(LocalDate date1, LocalDate date2) {
        return ordersMap.values().stream().filter(order ->
                        order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersForPeriodDateCreation(LocalDate date1, LocalDate date2) {
        return ordersMap.values().stream().filter(order ->
                        order.getDateCreated().isAfter(date1)
                                && order.getDateCreated().isBefore(date2))
                .collect(Collectors.toList());
    }
}
