package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class BookListByAvailability implements IAction {

    @Override
    public void execute() {
       bookService.printList("Books sorted by stock availability:",
                 bookService.getBooksSortedByStatus());
    }
}
