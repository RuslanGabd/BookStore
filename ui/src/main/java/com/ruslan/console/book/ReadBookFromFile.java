package com.ruslan.ui.console.book;


import com.ruslan.ui.console.IAction;
import com.ruslan.entity.book.Book;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class ReadBookFromFile extends ActionsBook implements IAction {

    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id book:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                if (bookService.getBookFromFile(id) != null) {
                    Book bk = bookService.getBookFromFile(id);
                    System.out.println("Book was gotten from file Books.csv");
                    System.out.println(bk);
                } else {
                    System.out.println("Book with id=" + id + " not found");
                }
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of Book ID");
                logger.error("Not number", e);
            }
        }
    }
}
