package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByStatus extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by date publication:");
        bookService.getBooksSortedByStatus().forEach(System.out::println);
    }
}
