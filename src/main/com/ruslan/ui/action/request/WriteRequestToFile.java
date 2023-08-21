package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;
import com.ruslan.ui.action.order.ActionsOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriteRequestToFile extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id request:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                requestService.writeOrderToFile(id);
                System.out.println("Request was written to file Requests.csv");
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of request ID");
            }
    }
}
