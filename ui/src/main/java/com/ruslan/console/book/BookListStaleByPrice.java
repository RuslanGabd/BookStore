package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListStaleByPrice extends ActionsBook implements IAction {
    @Override
    public void execute() {
        System.out.println("Stale books sorted by price:");
        bookService.getStaleBooksSortedByPrice().forEach(System.out::println);
    }
}
