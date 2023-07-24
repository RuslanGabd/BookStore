package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class OrderListByPrice implements IAction {

    @Override
    public void execute() {
        orderService.printList("Orders sorted by Price",
                orderService.getOrdersSortedByPrice());
    }
}
