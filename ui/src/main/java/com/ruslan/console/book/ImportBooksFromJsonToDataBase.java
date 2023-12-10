package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportBooksFromJsonToDataBase  extends ActionsBook implements IAction {
    public ImportBooksFromJsonToDataBase() {
    }

    public void execute() {
        bookService.importBooksFromJsonToDataBase();
    }
}
