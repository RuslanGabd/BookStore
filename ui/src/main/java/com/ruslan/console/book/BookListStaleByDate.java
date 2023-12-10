package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListStaleByDate extends ActionsBook implements IAction {
    @Override
    public void execute() {
        System.out.println("Stale books sorted by date: ");
        bookService.getStaleBooksSortedByDate().forEach(System.out::println);
    }
}
