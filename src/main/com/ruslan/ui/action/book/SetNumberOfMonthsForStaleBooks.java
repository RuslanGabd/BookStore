package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetNumberOfMonthsForStaleBooks extends ActionsBook implements IAction {
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter number of months to mark a book as \"stale\"");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                bookService.setNumberMonthsOfStaleBooks(s1);
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter number of months");
                logger.error("Not number", e);
            }
        }
    }
}
