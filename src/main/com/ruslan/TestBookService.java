package com.ruslan;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.context.ApplicationContext;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.ui.MenuController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBookService {

    public static void main(String[] args) throws IOException, ClassNotFoundException, JsonProcessingException {

        ApplicationContext applicationContext = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(applicationContext, new HashMap<>());
        applicationContext.setObjectFactory(objectFactory);
        MenuController menuController = applicationContext.getObject(MenuController.class);
        menuController.run();
    }
}
