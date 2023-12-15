package com.ruslan;


import com.ruslan.console.MenuController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MenuController menuController = context.getBean(MenuController.class);
        menuController.run();

    }
}
