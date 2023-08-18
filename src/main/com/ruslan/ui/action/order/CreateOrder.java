package com.ruslan.ui.action.order;

import com.ruslan.data.book.Book;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateOrder extends ActionsOrder implements IAction {
    final BookRepository bookRepository = BookRepository.getInstance();

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id books using comma if more than one book:");
                System.out.println("Example: 1,2,3,4");
                String s1 = reader.readLine();
                List<String> idBook = new ArrayList<String>(Arrays.asList(s1.split(",")));
                List<Book> bookList = idBook.stream()
                        .map(Integer::parseInt)
                        .map(bookRepository::getById)
                        .toList();
                orderService.createOrder(bookList);
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
