package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class RequestListByNumber implements IAction {
    @Override
    public void execute() {
        requestService.printList("Requests sorted by Number",
                requestService.getRequestSortedByNumber());
    }


}
