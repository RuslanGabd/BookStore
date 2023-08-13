package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;

public class BookListStaleByPrice implements IAction {
    @Override
    public void execute() {
        System.out.println("Stale books sorted by price:");
        bookService.getStaleBooksSortedByPrice().forEach(System.out::println);
    }
}
