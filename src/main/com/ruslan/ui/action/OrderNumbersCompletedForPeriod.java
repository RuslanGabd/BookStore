package com.ruslan.ui.action;

import com.ruslan.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;

public class OrderNumbersCompletedForPeriod implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter first date format: YYYY-MM-DD");
            String s1 = reader.readLine();
            LocalDate date1 = LocalDate.parse(s1);
            System.out.println("Enter second date format: YYYY-MM-DD");
            String s2 = reader.readLine();
            LocalDate date2 = LocalDate.parse(s2);
            System.out.println("Numbers of completed orders for period " + date1 + " - " + date2 + ": " +
                    orderService.getNumberFulfilledOrdersForPeriod(date1, date2) + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DateTimeException e) {
            System.out.println("You need enter date format: YYYY-MM-DD");
        }
    }
}
