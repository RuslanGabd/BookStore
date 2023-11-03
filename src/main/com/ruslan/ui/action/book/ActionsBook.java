package com.ruslan.ui.action.book;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ActionsBook {

    final Logger logger = LogManager.getLogger(ActionsBook.class);
    @Inject
    BookService bookService;

    public ActionsBook() {
    }

}
