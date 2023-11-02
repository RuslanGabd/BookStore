package com.ruslan.DI;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.DI.annotation.PostConstruct;
import com.ruslan.DI.context.ApplicationContext;
import com.ruslan.DI.postProcessor.ObjectPostProcessor;
import com.ruslan.DI.postProcessor.PostConstructorObjectPostProcessor;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
    @Getter
    private final ObjectConfigurator objectConfigurator;
    @Getter
    private final Configuration configuration;
    private final ApplicationContext applicationContext;
    private final Reflections scanner;

    public ObjectFactory(ApplicationContext applicationContext, Map<Class, Class> interfaceToImplementation) {
        this.applicationContext = applicationContext;
        this.configuration = new JavaConfiguration();
        this.objectConfigurator = new JavaObjectConfigurator(configuration.getPackageToScan(),
                interfaceToImplementation);
        this.scanner = new Reflections(configuration.getPackageToScan());
    }


    public <T> T getObject(Class<T> clazz) {

        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = objectConfigurator.getImplementationClass(clazz);
        }
        T object = null;
        try {
            object = implementationClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        List<Field> allFields = new ArrayList<>(Arrays.asList(implementationClass.getDeclaredFields()));
        allFields.addAll(Arrays.asList(implementationClass.getSuperclass().getDeclaredFields()));
        for (Field field : allFields.stream().filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
            field.setAccessible(true);
            try {
                field.set(object, applicationContext.getObject(field.getType()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        callPostProcessor(object);

        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return object;
    }

    public void callPostProcessor(Object object) {
        getObjectConfigurator().getScanner().getSubTypesOf(ObjectPostProcessor.class)
                .forEach(processor -> {
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
                });
        try {
            final PostConstructorObjectPostProcessor postProcessor = new PostConstructorObjectPostProcessor();
            postProcessor.process(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}