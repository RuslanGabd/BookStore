package com.ruslan.console.action.book;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByAlphabetically extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by Alphabetically:");
        bookService.getBooksSortedByTitleAlphabetically().forEach(System.out::println);
    }
}
