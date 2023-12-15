package com.ruslan.console.action.book;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByStatus extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by date publication:");
        bookService.getBooksSortedByStatus().forEach(System.out::println);
    }
}
