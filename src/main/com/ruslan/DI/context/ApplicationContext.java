package com.ruslan.DI.context;

import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.postProcessor.ObjectPostProcessor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private ObjectFactory objectFactory;
    private final Map<Class, Object> objectMap = new ConcurrentHashMap<>();

    public ApplicationContext() {
    }

    public <T> T getObject(Class<T> clazz) {
        if (objectMap.containsKey(clazz)) {
            return (T) objectMap.get(clazz);
        }

        T object = objectFactory.getObject(clazz);
        callPostProcessor(object);
        objectMap.put(clazz, object);
        return object;
    }

    public void callPostProcessor(Object object) {
        objectFactory.getObjectConfigurator().getScanner().getSubTypesOf(ObjectPostProcessor.class)
                .forEach(processor-> {
                    ObjectPostProcessor postProcessor;
                    try {
                        postProcessor = processor.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        postProcessor.process(object);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } );
    }

}
