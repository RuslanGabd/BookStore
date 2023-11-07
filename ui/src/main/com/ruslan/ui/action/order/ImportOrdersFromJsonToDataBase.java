package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

public class ImportOrdersFromJsonToDataBase extends ActionsOrder implements IAction {
    public ImportOrdersFromJsonToDataBase() {
    }

    public void execute() {
        orderService.importOrdersFromJson();
    }
}
