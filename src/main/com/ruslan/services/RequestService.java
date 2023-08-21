package com.ruslan.services;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IRequestService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        Optional.of(requestRepository.getRequestForBook(bookId)).ifPresent(request -> {
            requestRepository.removeRequest(request.getId());
        });
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public List<Request> getRequestSortedByAlphabetically() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(request -> request.getBook().getTitle()));
        return listReq;
    }
}
