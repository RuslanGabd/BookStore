package com.ruslan.DI.postProcessor;

import com.ruslan.DI.ObjectFactory;

import java.lang.reflect.InvocationTargetException;

public interface ObjectPostProcessor {
    void process(Object object) throws InvocationTargetException, IllegalAccessException;
}