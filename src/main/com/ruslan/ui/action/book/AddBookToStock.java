package com.ruslan.ui.action.book;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddBookToStock implements IAction {

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Add book to stock \n Enter id book");
                String s1 = reader.readLine();
                if (s1.equals("exit"))
                    break;
                int id = Integer.parseInt(s1);
                bookService.addBookToStockAndCancelRequests(id);
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of orders ID");
            }
    }
}
