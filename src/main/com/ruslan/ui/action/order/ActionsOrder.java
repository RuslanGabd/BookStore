package com.ruslan.ui.action.order;

import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.OrderService;

public class ActionsOrder {

    final OrderRepository orderRepository;
    final RequestRepository requestRepository;

    OrderService orderService;

    public ActionsOrder() {
        this.orderRepository = OrderRepository.getInstance();
        this.requestRepository = RequestRepository.getInstance();
        this.orderService = new OrderService(orderRepository, requestRepository);
    }

}
