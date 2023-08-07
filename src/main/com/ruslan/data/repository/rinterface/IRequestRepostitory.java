package com.ruslan.data.repository.rinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.request.Request;

import java.util.List;
import java.util.Map;

public interface IRequestRepostitory {

    Request getById(int id);

    void saveRequest(Request request);

    void removeById(int id);

    List<Request> getRequestList();
}
