package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListStaleByPrice extends ActionsBook implements IAction {
    @Override
    public void execute() {
        System.out.println("Stale books sorted by price:");
        bookService.getStaleBooksSortedByPrice().forEach(System.out::println);
    }
}
