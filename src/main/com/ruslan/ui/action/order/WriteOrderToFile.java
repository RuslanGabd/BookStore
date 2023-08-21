package com.ruslan.ui.action.order;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriteOrderToFile extends ActionsOrder implements IAction {

    @Override
    public void execute() {
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id order:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                orderService.writeOrderToFile(id);
                System.out.println("Order was written to file Orders.csv");
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter numbers of order ID");
            }
    }
}
