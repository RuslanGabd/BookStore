package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

public class ExportOrdersToJson extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        orderService.exportOrdersToJson();
    }
}
