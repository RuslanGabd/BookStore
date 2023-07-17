package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

public class BookListByPrice implements IAction {

    @Override
    public void execute() {
        bookService.printList("Books sorted by price:",
                 bookService.getBooksSortedByPrice());
    }
}
