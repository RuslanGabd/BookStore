package com.ruslan.ui.action.request;

import com.ruslan.ui.IAction;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class WriteRequestToFile extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id request:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                requestService.writeRequestToFile(id);
                System.out.println("Request was written to file Requests.csv");
                break;
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("You need enter number of request ID");
                logger.error("Wrong number format", e);
            }
        }
    }
}
