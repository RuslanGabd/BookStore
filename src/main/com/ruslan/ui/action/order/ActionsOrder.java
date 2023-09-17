package com.ruslan.ui.action.order;

import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.OrderService;
import com.ruslan.ui.action.book.ActionsBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionsOrder {
    static final Logger logger = LogManager.getLogger(ActionsOrder.class);
    private   final OrderRepository orderRepository;
    private  final RequestRepository requestRepository;

    OrderService orderService;

    public ActionsOrder() {
        this.orderRepository = OrderRepository.getInstance();
        this.requestRepository = RequestRepository.getInstance();
        this.orderService = new OrderService(orderRepository, requestRepository);
    }

}
