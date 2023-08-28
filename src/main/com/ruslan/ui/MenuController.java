package com.ruslan.ui;

import com.ruslan.ui.action.request.ActionsRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {
    private static final Logger logger = LogManager.getLogger();
    private Builder builder = new Builder();
    private Navigator navigator;

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        builder.buildMenu();
        Menu rootMenu = builder.getRootMenu();

        navigator = new Navigator(rootMenu);
        navigator.printMenu();
        System.out.println("\nChoose number of menu");
        String s;
        Integer selectedItem;

        while (true) {
            try {
                s = reader.readLine();
                if (s.equals("exit")) {
                    break;
                }
                selectedItem = Integer.valueOf(s);
                navigator.navigate(selectedItem);
                navigator.printMenu();
                System.out.println("\nChoose number of menu");
            } catch (IOException e) {
                System.out.println("Something went wrong.\n" +
                        "If error will repeat please sent information to example@gmail.com");
                logger.error("Something went wrong.", e);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number. For finish write: exit");
                logger.error("Wrong number.", e);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong number. Please choose number of menu");
                logger.error("Wrong number.", e);
            }
        }
    }

}


