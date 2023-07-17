package com.ruslan.ui.action;

import com.ruslan.ui.IAction;
import lombok.Data;

@Data
public class  BookListByAlphabetService implements IAction {

    @Override
    public void execute() {
        bookService.printList("Books sorted by Title Alphabetically:",
                 bookService.getBooksSortedByTitleAlphabetically());
    }
}
