package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;

public class BookListStaleForPeriodByPrice implements IAction {
    @Override
    public void execute() {
        bookService.printList("Stale books sorted by price: ",
                bookService.getStaleBooksSortedByPrice());
    }
}
