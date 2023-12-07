package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportOrdersFromJsonToDataBase extends ActionsOrder implements IAction {
    public ImportOrdersFromJsonToDataBase() {
    }

    public void execute() {
        orderService.importOrdersFromJson();
    }
}
