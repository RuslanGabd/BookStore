package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class BookListByPrice extends ActionsBook implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by price: ");
        bookService.getBooksSortedByPrice().forEach(System.out::println);
    }
}
