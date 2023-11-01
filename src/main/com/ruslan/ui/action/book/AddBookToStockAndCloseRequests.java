package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddBookToStockAndCloseRequests extends ActionsBook implements IAction {

    @Override
    public void execute() {

        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Add book to stock \n Enter id book");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                bookService.addBookToStockAndCancelRequests(id);
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of Book ID");
                logger.error("Wrong format number", e);
            }
        }
    }
}
