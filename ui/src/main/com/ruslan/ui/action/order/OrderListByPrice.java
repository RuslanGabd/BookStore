package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

public class OrderListByPrice extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        System.out.println("Orders sorted by Price: ");
        orderService.getOrdersSortedByPrice().forEach(System.out::println);
    }
}
