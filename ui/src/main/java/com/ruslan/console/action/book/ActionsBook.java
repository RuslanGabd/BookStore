package com.ruslan.console.action.book;

import com.ruslan.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class ActionsBook {

    final Logger logger = LogManager.getLogger(ActionsBook.class);
    @Autowired
    BookService bookService;

    public ActionsBook() {
    }

}
