package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

public class BookListStaleByDate extends ActionsBook implements IAction {
    @Override
    public void execute() {
        System.out.println("Stale books sorted by date: ");
        bookService.getStaleBooksSortedByDate().forEach(System.out::println);
    }
}
