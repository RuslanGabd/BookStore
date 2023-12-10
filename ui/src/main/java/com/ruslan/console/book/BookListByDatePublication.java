package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByDatePublication extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by date publication:");
        bookService.getBooksSortedByDatePublication().forEach(System.out::println);
    }
}
