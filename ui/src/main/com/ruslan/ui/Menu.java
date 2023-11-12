package com.ruslan.ui;


import java.util.List;

public class Menu {

    private List<MenuItem> menuItems;
    private String name;

    public Menu(String name, List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.name = name;
    }

    public Menu() {
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
