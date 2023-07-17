package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class BookListByDatePublication implements IAction {

    @Override
    public void execute() {
        bookService.printList("Books sorted by date publication:",
                 bookService.getBooksSortedByDatePublication());
    }
}
