package com.ruslan.ui.action.request;


import com.ruslan.ui.IAction;
import com.ruslan.entity.request.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateRequest extends ActionsRequest implements IAction {


    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Create request. Enter id book:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                requestService.createRequest(new Request(bookDao.findById(id).orElse(null)));
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter number of book ID");
                logger.error("Wrong number format", e);
            }
        }
    }
}
