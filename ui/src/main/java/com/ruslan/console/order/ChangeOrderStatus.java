package com.ruslan.ui.console.order;


import com.ruslan.ui.console.IAction;
import com.ruslan.entity.order.OrderStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class ChangeOrderStatus extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id order:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                System.out.println("Enter status NEW, COMPLETED, CANCELLED:");
                String s2 = reader.readLine();
                OrderStatus status = OrderStatus.valueOf(s2);
                orderService.changeOrderStatus(id, status);
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of order ID");
                logger.error("Not number", e);
            }
        }
    }
}
