package com.ruslan.entity.repository.rinterface;

import com.ruslan.entity.request.Request;

import java.util.List;

public interface IRequestRepository {

    Request getById(int id);

    void saveRequest(Request request);

    void removeById(int id);


    List<Request> getRequestList();

    Request getRequestForBook(int id);

    void removeRequest(int id);
}
