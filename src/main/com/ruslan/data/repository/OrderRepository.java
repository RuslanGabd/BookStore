package com.ruslan.data.repository;

import com.ruslan.DI.annotation.PostConstruct;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderCounted;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.rinterface.IOrderRepository;
import com.ruslan.jsonHandlers.JsonReader;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderRepository implements IOrderRepository {

    //   public static final OrderRepository INSTANCE = new OrderRepository();

    public static final String pathOrdersJSON = "src\\main\\resources\\Orders.json";
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final Map<Integer, Order> ordersMap = new HashMap<>();

    @PostConstruct
    public void importOrdersFromJson() {
        List<Order> orderList = jsonReader.readEntities(Order.class, pathOrdersJSON);
        orderList.forEach(order -> this.addOrder(order.getId(), order));
    }


    @Override
    public void saveOrder(Order order) {
        int id = OrderCounted.generateNewId();
        order.setId(id);
        ordersMap.put(id, order);
    }

    public void addOrder(int idOrder, Order order) {
        ordersMap.put(idOrder, order);
    }

    @Override
    public List<Order> getOrdersList() {
        return new ArrayList<>(ordersMap.values());
    }

    @Override
    public Optional<Order> getById(int id) {
        return Optional.of(ordersMap.get(id));
    }


    @Override
    public void updateStatus(int id, OrderStatus status) {
        getById(id).ifPresent(order -> order.setStatus(status));
    }

    @Override
    public void removeById(int id) {
        ordersMap.remove(id);
    }

    @Override
    public void setDateExecution(int id, LocalDate date) {
        getById(id).ifPresent(order -> order.setDateExecution(date));
    }

    @Override
    public void addOrder(Integer id, Order order) {

    }

    public List<Order> getCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        return ordersMap.values().stream().filter(order ->
                        order.getStatus() == OrderStatus.COMPLETED
                                && order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .collect(Collectors.toList());
    }


}
