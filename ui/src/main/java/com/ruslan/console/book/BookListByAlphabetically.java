package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByAlphabetically extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by Alphabetically:");
        bookService.getBooksSortedByTitleAlphabetically().forEach(System.out::println);
    }
}
