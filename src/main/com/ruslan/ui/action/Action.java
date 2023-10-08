package com.ruslan.ui.action;


import com.ruslan.DI.annotation.Inject;
import com.ruslan.data.repository.rinterface.IBookRepository;
import com.ruslan.data.repository.rinterface.IOrderRepository;
import com.ruslan.data.repository.rinterface.IRequestRepository;

public class Action {
    @Inject
    private IBookRepository bookRepository;
    @Inject
    private IOrderRepository orderRepository;
    @Inject
    private IRequestRepository requestRepository;

    public Action() {
    }
}
