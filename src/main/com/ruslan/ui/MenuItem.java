package com.ruslan.ui;

import lombok.Data;

@Data
public class MenuItem {
    private String title;

    private IAction action;

    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        action.execute();
    }
}
