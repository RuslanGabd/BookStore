package com.ruslan.data.repository;

import com.ruslan.data.book.Book;
import com.ruslan.data.order.Order;
import com.ruslan.data.repository.rinterface.IRequestRepostitory;
import com.ruslan.data.request.Request;
import com.ruslan.data.request.RequestCounted;

import java.util.*;
import java.util.stream.Collectors;

public class RequestRepository implements IRequestRepostitory {

    public static final RequestRepository INSTANCE = new RequestRepository();


    private Map<Integer, Request> requestMap = new HashMap<>();

    public void removeRequest(int id) {
        requestMap.remove(id);
    }

    public void saveRequest(Request request) {
        int idRequest = RequestCounted.generateNewId();
        request.setId(idRequest);
        requestMap.put(idRequest, request);
        System.out.print("New request created with id=" + request.getId());
        System.out.println(" Total requests: " + requestMap.size());
    }
    public void addOrder(int idRequest, Request request) {
        requestMap.put(idRequest, request);
    }
    @Override
    public Request getById(int id) {
        return requestMap.get(id);
    }


    @Override
    public void removeById(int id) {
        requestMap.remove(id);
    }

    @Override
    public List<Request> getRequestList() {
        return new ArrayList<>(requestMap.values());
    }

    public void createRequest(Book book) {
    }

    public Request getRequestForBook(int bookId) {
        return requestMap.values()
                .stream()
                .filter(request
                        -> request.getBook().getId() == bookId)
                .findAny()
                .orElse(null);
    }

    public static RequestRepository getInstance() {
        return INSTANCE;
    }
}
