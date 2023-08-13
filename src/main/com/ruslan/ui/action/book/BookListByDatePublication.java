package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

public class BookListByDatePublication implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by date publication:");
        bookService.getBooksSortedByDatePublication().forEach(System.out::println);
    }
}
