package com.ruslan.console.action.order;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportOrdersToJson extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        orderService.exportOrdersToJson();
    }
}
