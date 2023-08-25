package com.ruslan.ui.action.order;

import com.ruslan.data.order.Order;
import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadOrderFromFile extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id order:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                Order ord = orderService.getOrderFromFile(id);
                if (ord != null) {
                    System.out.println("Order was gotten from file Order.CSV");
                    System.out.println(ord);
                    ord.getListBook().forEach(System.out::println);
                } else {
                    System.out.println("Order with id=" + id + " not found");
                }
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of order ID");
            }
    }
}
