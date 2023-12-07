package com.ruslan.ui;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
@Component
public class Navigator {
    private Menu currentMenu;

    public Navigator(){}

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        final AtomicInteger count = new AtomicInteger();
        System.out.println(currentMenu.getName() + ":");
        currentMenu.getMenuItems().stream().map(MenuItem::getTitle)
                .forEach(title -> System.out.println(count.getAndIncrement() + " " + title));

    }

    public void navigate(Integer index) {
        if (currentMenu.getMenuItems().get(index).getAction() != null) {
            currentMenu.getMenuItems().get(index).doAction();
        }
         currentMenu = currentMenu.getMenuItems().get(index).getNextMenu();
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
