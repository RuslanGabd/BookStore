package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class OrderListByStatus implements IAction {

    @Override
    public void execute() {
        orderService.printList("Orders sorted by Status",
                orderService.getOrdersSortedByStatus());
    }
}
