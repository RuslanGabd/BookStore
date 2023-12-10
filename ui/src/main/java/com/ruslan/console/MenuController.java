package com.ruslan.ui.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MenuController {
    private final Logger logger = LogManager.getLogger();
    @Autowired
    private Builder builder;
    @Autowired
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


