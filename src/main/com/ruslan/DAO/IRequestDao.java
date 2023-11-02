package com.ruslan.DAO;

import com.ruslan.data.request.Request;

import java.util.List;
import java.util.Optional;

public interface IRequestDao {

    Optional<Request> findById(int id);

    Optional<Request> findRequestByBookId(int id);

    Request saveRequest(Request request);

    Boolean removeByRequestId(int id);

    Boolean removeByBookId(int id);

    List<Request> getRequestsList();


}
