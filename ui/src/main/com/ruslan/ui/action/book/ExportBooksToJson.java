package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

@Component
public class ExportBooksToJson extends ActionsBook implements IAction {

    @Override
    public void execute() {
        bookService.exportBooksToJson();
    }
}
