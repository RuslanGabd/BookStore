package com.ruslan.console.action.book;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportBooksFromJsonToDataBase  extends ActionsBook implements IAction {
    public ImportBooksFromJsonToDataBase() {
    }

    public void execute() {
        bookService.importBooksFromJsonToDataBase();
    }
}
