package com.ruslan.console.action.order;

import com.ruslan.database.DAO.BookRepository;
import com.ruslan.entity.book.Book;
import com.ruslan.console.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
@Component
public class CreateOrder extends ActionsOrder implements IAction {

    @Autowired
    private BookRepository bookRepository;
    private final  String checkNumbersSeparatedComma = "((?<!^,)\\d+(,(?!$)|$))+";

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
                            .map(n->bookRepository.findById(n).get()).toList();
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
