package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class OrderListByDateExecution implements IAction {

    @Override
    public void execute() {
        orderService.printList("Orders sorted by Date", orderService.getOrdersSortedByDateExecution());
    }
}
