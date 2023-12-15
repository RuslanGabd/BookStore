package com.ruslan.console.action.order;


import com.ruslan.console.IAction;
import com.ruslan.entity.order.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class ReadOrderFromFile extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        while (true) {
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
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of order ID");
                logger.error("Wrong format number", e);
            }
        }
    }
}
