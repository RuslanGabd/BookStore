package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByPrice extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by price: ");
        bookService.getBooksSortedByPrice().forEach(System.out::println);
    }
}
