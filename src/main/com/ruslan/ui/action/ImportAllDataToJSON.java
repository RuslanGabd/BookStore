package com.ruslan.ui.action;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.services.BookService;
import com.ruslan.services.OrderService;
import com.ruslan.services.RequestService;
import com.ruslan.ui.IAction;

public class ImportAllDataToJSON extends Action implements IAction {

    public void execute() {
    //    bookService.importBooksFromJson();
        orderService.importOrdersFromJson();
        requestService.importRequestsFromJson();
    }
}
