package com.ruslan.ui.console.request;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class RequestListByNumber extends ActionsRequest implements IAction {
    @Override
    public void execute() {
        System.out.println("Requests sorted by Number: ");
        requestService.getRequestSortedByNumber().forEach(System.out::println);
    }
}
