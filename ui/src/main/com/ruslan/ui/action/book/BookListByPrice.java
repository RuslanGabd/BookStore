package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

public class BookListByPrice extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by price: ");
        bookService.getBooksSortedByPrice().forEach(System.out::println);
    }
}
