package com.ruslan.ui.console.order;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportOrdersFromJsonToDataBase extends ActionsOrder implements IAction {
    public ImportOrdersFromJsonToDataBase() {
    }

    public void execute() {
        orderService.importOrdersFromJson();
    }
}
