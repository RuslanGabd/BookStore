package com.ruslan.console.action.order;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportOrdersFromJsonToDataBase extends ActionsOrder implements IAction {
    public ImportOrdersFromJsonToDataBase() {
    }

    public void execute() {
        orderService.importOrdersFromJson();
    }
}
