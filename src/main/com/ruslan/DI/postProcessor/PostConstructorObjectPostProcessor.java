package com.ruslan.DI.postProcessor;

import com.ruslan.DI.annotation.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PostConstructorObjectPostProcessor implements ObjectPostProcessor {
    @Override
    public void process(Object object) throws InvocationTargetException, IllegalAccessException {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(object);
            }
        }
    }
}
