package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;

public class RequestListByNumber extends ActionsRequest implements IAction {
    @Override
    public void execute() {
        System.out.println("Requests sorted by Number: ");
        requestService.getRequestSortedByNumber().forEach(System.out::println);
    }
}
