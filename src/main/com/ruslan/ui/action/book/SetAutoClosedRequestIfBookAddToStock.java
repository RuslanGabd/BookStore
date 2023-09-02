package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetAutoClosedRequestIfBookAddToStock extends ActionsBook implements IAction {

    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter \"on\" or \"off\" for change  the ability to mark requests as completed");
                String s1 = reader.readLine();
                if (s1.equals("on") || s1.equals("off")) {
                    bookService.setAutoClosedRequestIfBookAddToStock(s1);
                    break;
                } else {
                    System.out.println("You need enter \"on\" or \"off\"");
                }
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
