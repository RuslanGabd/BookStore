package com.ruslan.ui.action.request;

import com.ruslan.data.order.Order;
import com.ruslan.data.request.Request;
import com.ruslan.ui.IAction;
import com.ruslan.ui.action.order.ActionsOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadRequestFromFile extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id request:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                Request req = requestService.getRequestFromFile(id);
                System.out.println(req);
                if (req != null) {
                    System.out.println("Request was gotten from file Requests.csv");
                    System.out.println(req);
                } else {
                    System.out.println("Request with id=" + id + " not found");
                }
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of request ID");
            }
    }
}
