package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportOrdersToJson extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        orderService.exportOrdersToJson();
    }
}
