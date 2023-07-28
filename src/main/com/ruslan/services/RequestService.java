package com.ruslan.services;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IRequestService;

import java.util.*;

public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;
    private final BookRepository bookRepository;

    public RequestService(RequestRepository requestRepository, BookRepository bookRepository) {
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createRequest(int bookId) {
        requestRepository.saveRequest(new Request(bookRepository.getById(bookId)));
    }

    public void cancelRequestsById(int requestId) {
        requestRepository.removeRequest(requestId);
        System.out.println("Request id=" + requestId + " canceled");
    }

    public void cancelRequestsByBookId(int bookId) {
        for (Request req : requestRepository.getRequestList())
            if (req.getBook().getId() == bookId) {
                requestRepository.removeRequest(req.getId());
                System.out.println("Request id=" + req.getId() + " canceled");
            }
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public List<Request> getRequestSortedByAlphabetically() {
        Map<String, Request> requestTreeMap = new TreeMap<>();
        requestRepository.getRequestList()
                .forEach(request
                        -> requestTreeMap.put(request.getBook().getTitle(), request));
        return new ArrayList<Request>(requestTreeMap.values());
    }
}
