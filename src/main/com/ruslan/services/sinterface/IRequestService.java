package com.ruslan.services.sinterface;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.request.Request;

import java.util.List;

public interface IRequestService {

    void printList(String header, List<Request> list);

    void createRequest(int id);


}
