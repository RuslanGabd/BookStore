package com.ruslan.ui.action.order;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionsOrder {
    final Logger logger = LogManager.getLogger(ActionsOrder.class);
    @Inject
    OrderService orderService;

    public ActionsOrder() {
    }

}
