package com.ruslan;


import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.context.ApplicationContext;
import com.ruslan.ui.MenuController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class TestBookService {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(applicationContext, new HashMap<>());
        applicationContext.setObjectFactory(objectFactory);
        MenuController menuController = applicationContext.getObject(MenuController.class);
        menuController.run();

    }
}
