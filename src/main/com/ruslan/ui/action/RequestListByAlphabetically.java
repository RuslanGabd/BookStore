package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class RequestListByAlphabetically implements IAction {
    @Override
    public void execute() {
        requestService.printMap("Requests sorted by Alphabetically",
                requestService.getRequestSortedByAlphabetically());
    }


}
