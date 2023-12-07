package com.ruslan.ui.action.request;


import com.ruslan.ui.IAction;
import com.ruslan.entity.request.Request;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class ReadRequestFromFile extends ActionsRequest implements IAction {

    @Override
    public void execute() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter id request:");
                String s1 = reader.readLine();
                int id = Integer.parseInt(s1);
                Request req = requestService.getRequestFromFile(id);
                if (req != null) {
                    System.out.println("Request was gotten from file Requests.csv");
                    System.out.println(req);
                } else {
                    System.out.println("Request with id=" + id + " not found");
                }
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
