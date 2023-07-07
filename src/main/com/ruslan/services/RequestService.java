package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
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
    public void printList(String header, List<Request> list) {
        System.out.println(header);
        for (Request req : list) {
            System.out.println(req.toString() + "; ");
        }
        System.out.println();
    }
    @Override
    public void createRequest(Book book) {
        requestRepository.saveRequest(new Request(book));
    }

    public void cancelRequestsUseId(int id) {
        requestRepository.removeRequest(id);
        System.out.println("Request id=" + id + " canceled");
    }

    public void cancelRequestsForBook(Book book) {
        for (Request req : requestRepository.getRequestList())
            if (req.getBook().equals(book)) {
                requestRepository.removeRequest(req.getId());
                System.out.println("Request id=" + req.getId() + " canceled");
            }
    }

    public List<Order> createListOrdersFulfilled(List<Order> list) {
        List<Order> listFulfilledOrders = new ArrayList<>();
        for (Order ord : list) {
            if (ord.getStatus() == OrderStatus.FULFILLED) {
                listFulfilledOrders.add(ord);
            }
        }
        return listFulfilledOrders;
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public Map<String, Request> getRequestSortedByAlphabetically() {
        Map<String, Request> requestTreeMap = new TreeMap<>();
        requestRepository.getRequestList().forEach(request -> requestTreeMap.put(request.getBook().getTitle(), request));
        return requestTreeMap;
    }

    public void printMap(String header, Map<String, Request> requestMap) {
        System.out.println(header);
        for (Map.Entry<String, Request> entry : requestMap.entrySet()) {
            System.out.println("Title book: "+entry.getKey() + " : " + entry.getValue().toString());
        }
    }
}
