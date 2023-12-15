package com.ruslan.console.action.book;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportBooksToJson extends ActionsBook implements IAction {

    @Override
    public void execute() {
        bookService.exportBooksToJson();
    }
}
