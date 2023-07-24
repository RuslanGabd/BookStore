package com.ruslan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {

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
                if (s.equals("exit"))
                    break;
                selectedItem = Integer.valueOf(s);
                navigator.navigate(selectedItem);
                navigator.printMenu();
                System.out.println("\nChoose number of menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("You need choose number of menu. For finish write: exit");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong number. Please choose number of menu");
            }
        }
    }
}


