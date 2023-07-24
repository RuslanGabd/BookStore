package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderDetails implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter id order:");
            String s1 = reader.readLine();
            int id = Integer.parseInt(s1);
            orderService.printOrderDetails(id);

//            System.out.println("Earned money for period " + date1 + " - " + date2 + ": " +
//                    orderService.getEarnedMoneyForPeriod(date1, date2) + " €\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("You need enter numbers of orders ID");
        }
    }
}
