package com.ruslan.ui.console.request;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportRequestsFromJsonToDataBase extends ActionsRequest implements IAction {
    public ImportRequestsFromJsonToDataBase() {
    }

    public void execute() {
        requestService.importRequestsFromJsonToDataBase();
    }
}
