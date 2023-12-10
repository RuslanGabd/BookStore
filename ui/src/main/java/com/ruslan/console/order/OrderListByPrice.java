package com.ruslan.ui.console.order;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class OrderListByPrice extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        System.out.println("Orders sorted by Price: ");
        orderService.getOrdersSortedByPrice().forEach(System.out::println);
    }
}
