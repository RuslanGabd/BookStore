package com.ruslan.entity.repository;

import com.ruslan.entity.repository.rinterface.IRequestRepository;
import com.ruslan.entity.request.Request;
import com.ruslan.entity.request.RequestCounted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRepository implements IRequestRepository {

    public static final String pathRequestJSON = "src\\main\\resources\\Requests.json";


    private final Map<Integer, Request> requestMap = new HashMap<>();

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


    public Request getRequestForBook(int bookId) {
        return requestMap.values()
                .stream()
             //   .filter(request
                       // -> request.getBook().getId() == bookId)
                .findAny()
                .orElse(null);
    }


}
