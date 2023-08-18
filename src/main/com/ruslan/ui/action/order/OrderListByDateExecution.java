package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

public class OrderListByDateExecution extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        System.out.println("Orders sorted by Date");
        orderService.getOrdersSortedByDateExecution().forEach(System.out::println);
    }
}
