package com.ruslan.ui.action.request;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.RequestService;

public class ActionsRequest {
    final RequestRepository requestRepository;
    final BookRepository bookRepository;
    RequestService requestService;
    public ActionsRequest() {
        this.requestRepository = RequestRepository.getInstance();
        this.bookRepository = BookRepository.getInstance();
        this.requestService = new RequestService(requestRepository, bookRepository);
    }
}
