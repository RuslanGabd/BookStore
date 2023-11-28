package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class RequestListByAlphabetically extends ActionsRequest implements IAction {
    @Override
    public void execute() {
        System.out.println("Requests sorted by Alphabetically");
        requestService.getRequestSortedByAlphabetically().forEach(System.out::println);
    }
}
