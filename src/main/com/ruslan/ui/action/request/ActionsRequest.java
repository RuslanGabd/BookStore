package com.ruslan.ui.action.request;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionsRequest {
    static final Logger logger = LogManager.getLogger(ActionsRequest.class);
    final RequestRepository requestRepository;
    final BookRepository bookRepository;
    RequestService requestService;
    public ActionsRequest() {
        this.requestRepository = RequestRepository.getInstance();
        this.bookRepository = BookRepository.getInstance();
        this.requestService = new RequestService(requestRepository, bookRepository);
    }
}
