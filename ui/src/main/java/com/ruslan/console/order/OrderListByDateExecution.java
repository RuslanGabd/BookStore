package com.ruslan.ui.console.order;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class OrderListByDateExecution extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        System.out.println("Orders sorted by Date");
        orderService.getOrdersSortedByDateExecution().forEach(System.out::println);
    }
}
