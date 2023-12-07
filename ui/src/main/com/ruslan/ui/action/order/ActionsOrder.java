package com.ruslan.ui.action.order;

import com.ruslan.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ActionsOrder {
    final Logger logger = LogManager.getLogger(ActionsOrder.class);
    @Autowired
    OrderService orderService;

    public ActionsOrder() {
    }

}
