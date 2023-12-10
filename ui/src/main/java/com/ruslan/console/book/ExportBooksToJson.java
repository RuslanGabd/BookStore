package com.ruslan.ui.console.book;

import com.ruslan.ui.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportBooksToJson extends ActionsBook implements IAction {

    @Override
    public void execute() {
        bookService.exportBooksToJson();
    }
}
