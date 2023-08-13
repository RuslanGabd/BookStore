package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

public class OrderListByStatus implements IAction {

    @Override
    public void execute() {
        System.out.println("Orders sorted by Status");
        orderService.getOrdersSortedByStatus().forEach(System.out::println);
    }
}
