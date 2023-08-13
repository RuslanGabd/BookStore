package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import lombok.Data;

@Data
public class BookListByAlphabetically implements IAction {

    @Override
    public void execute() {
        System.out.println("Books sorted by Alphabetically:");
        bookService.getBooksSortedByTitleAlphabetically().forEach(System.out::println);
    }
}
