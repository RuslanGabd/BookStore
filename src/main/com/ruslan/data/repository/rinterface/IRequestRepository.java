package com.ruslan.data.repository.rinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.request.Request;

import java.util.List;
import java.util.Map;

public interface IRequestRepository {

    Request getById(int id);

    void saveRequest(Request request);

    void removeById(int id);


    List<Request> getRequestList();

    Request getRequestForBook(int id);

    void removeRequest(int id);
}
