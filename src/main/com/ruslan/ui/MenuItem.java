package com.ruslan.ui;

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

    public String getTitle() {
        return title;
    }

    public IAction getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }
}