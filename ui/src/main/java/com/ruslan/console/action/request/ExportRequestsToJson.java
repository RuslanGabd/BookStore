package com.ruslan.console.action.request;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportRequestsToJson extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        requestService.exportRequestsToJson();
    }
}
