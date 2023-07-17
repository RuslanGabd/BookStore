package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

import java.time.LocalDate;

import static com.ruslan.ui.IAction.orderService;

public class OrderListCompletedForPeriod  {


    public void execute(LocalDate date1, LocalDate date2) {
        orderService.printList("", orderService.getOrdersSortedByDateForPeriod(date1, date2));
    }
}
