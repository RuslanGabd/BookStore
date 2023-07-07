package com.ruslan.data.repository;

import com.ruslan.data.book.Book;
import com.ruslan.data.repository.rinterface.IRequestRepostitory;
import com.ruslan.data.request.Request;
import com.ruslan.data.request.RequestCounted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRepository implements IRequestRepostitory {

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

    public Map<Integer, Request> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<Integer, Request> requestMap) {
        this.requestMap = requestMap;
    }
}
