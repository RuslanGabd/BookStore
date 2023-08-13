package com.ruslan.ui.action.order;

import com.ruslan.data.order.OrderStatus;
import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeOrderStatus implements IAction {

    @Override
    public void execute() {
        while (true)
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
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of orders ID");
            }
    }
}
