package com.ruslan.console.action.request;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportRequestsFromJsonToDataBase extends ActionsRequest implements IAction {
    public ImportRequestsFromJsonToDataBase() {
    }

    public void execute() {
        requestService.importRequestsFromJsonToDataBase();
    }
}
