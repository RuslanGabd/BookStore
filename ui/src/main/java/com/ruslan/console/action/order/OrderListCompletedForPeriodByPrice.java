package com.ruslan.console.action.order;

import com.ruslan.console.IAction;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
@Component
public class OrderListCompletedForPeriodByPrice extends ActionsOrder implements IAction {

    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter first date format: YYYY-MM-DD");
                String s1 = reader.readLine();
                LocalDate date1 = LocalDate.parse(s1);
                System.out.println("Enter second date format: YYYY-MM-DD");
                String s2 = reader.readLine();
                LocalDate date2 = LocalDate.parse(s2);
                System.out.println("Completed orders for period sorted by status:" +
                        orderService.getCompletedOrdersSortedByPriceForPeriod(date1, date2));
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (DateTimeException e) {
                System.out.println("You need enter date format: YYYY-MM-DD");
                logger.error("Wrong date format", e);
            }
        }
    }
}
