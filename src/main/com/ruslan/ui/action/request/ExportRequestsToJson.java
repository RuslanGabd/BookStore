package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;

public class ExportRequestsToJson extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        requestService.exportRequestsToJson();
    }
}
