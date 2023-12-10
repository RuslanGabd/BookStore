package com.ruslan.ui.console.request;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportRequestsToJson extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        requestService.exportRequestsToJson();
    }
}
