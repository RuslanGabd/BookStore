package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;

public class RequestListByAlphabetically extends ActionsRequest implements IAction {
    @Override
    public void execute() {
        System.out.println("Requests sorted by Alphabetically");
        requestService.getRequestSortedByAlphabetically().forEach(System.out::println);
    }
}
