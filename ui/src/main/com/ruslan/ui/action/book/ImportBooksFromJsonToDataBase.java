package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class ImportBooksFromJsonToDataBase  extends ActionsBook implements IAction {
    public ImportBooksFromJsonToDataBase() {
    }

    public void execute() {
        bookService.importBooksFromJsonToDataBase();
    }
}
