package com.ruslan.ui.console.request;


import com.ruslan.database.DAO.BookRepository;
import com.ruslan.services.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ActionsRequest {
    final Logger logger = LogManager.getLogger(ActionsRequest.class);
    @Autowired
    RequestService requestService;
    @Autowired
    BookRepository bookRepository;

    public ActionsRequest() {

    }
}
