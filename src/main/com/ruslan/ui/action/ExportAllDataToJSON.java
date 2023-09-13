package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class ExportAllDataToJSON extends Action implements IAction {


    public void execute() {
        bookService.exportBooksToJson();
        orderService.exportOrdersToJson();
        requestService.exportRequestsToJson();

    }
}
