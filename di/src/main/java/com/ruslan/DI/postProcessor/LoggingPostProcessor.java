package com.ruslan.DI.postProcessor;

import java.lang.reflect.InvocationTargetException;

public class LoggingPostProcessor implements ObjectPostProcessor {


    @Override
    public void process(Object object) throws InvocationTargetException, IllegalAccessException {
        System.out.println("logging"+ object.getClass());
    }
}
