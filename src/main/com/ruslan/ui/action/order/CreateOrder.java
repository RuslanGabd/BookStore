package com.ruslan.ui.action.order;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.data.book.Book;
import com.ruslan.data.repository.rinterface.IBookRepository;
import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CreateOrder extends ActionsOrder implements IAction {

    @Inject
    private IBookRepository bookRepository;
    private final static String checkNumbersSeparatedComma = "((?<!^,)\\d+(,(?!$)|$))+";

    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id books using comma if more than one book:");
                System.out.println("Example: 1,2,3,4");
                String s1 = reader.readLine();
                if (s1.matches(checkNumbersSeparatedComma)) {
                    List<Book> bookList = Arrays.stream(s1.split(",")).map(Integer::parseInt)
                            .map(bookRepository::getById).toList();
                    orderService.createOrder(bookList);
                    break;
                } else {
                    System.out.println("You need enter a book ID to create an order");
                }
            } catch (IOException e) {
                logger.error("Error", e);
            }
        }
    }
}
