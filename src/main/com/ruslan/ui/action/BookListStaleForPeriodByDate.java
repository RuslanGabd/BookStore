package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class BookListStaleForPeriodByDate implements IAction {
    @Override
    public void execute() {
        bookService.printList("Stale books sorted by date: ",
                bookService.getStaleBooksSortedByDate());
    }
}
