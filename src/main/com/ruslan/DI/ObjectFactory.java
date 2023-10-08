package com.ruslan.DI;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.DI.context.ApplicationContext;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectFactory {

    Reflections scanner;
    @Getter
    private final ObjectConfigurator objectConfigurator;

    @Getter
    private final Configuration configuration;
    private ApplicationContext applicationContext;

    public ObjectFactory(ApplicationContext applicationContext, Map<Class, Class> interfaceToImplementation) {
        this.applicationContext = applicationContext;
        this.configuration = new JavaConfiguration();
        this.objectConfigurator = new JavaObjectConfigurator(configuration.getPackageToScan(),
                interfaceToImplementation);
        this.scanner = new Reflections(configuration.getPackageToScan());
    }

    @SneakyThrows
    public <T> T getObject(Class<T> clazz) {

        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = objectConfigurator.getImplementationClass(clazz);
        }
        T object = implementationClass.getDeclaredConstructor().newInstance();
        List<Field> allFields = new ArrayList<>(Arrays.asList(implementationClass.getDeclaredFields()));
        allFields.addAll(Arrays.asList(implementationClass.getSuperclass().getDeclaredFields()));
        for (Field field : allFields.stream().filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
            field.setAccessible(true);
            field.set(object, applicationContext.getObject(field.getType()));
        }
        return object;
    }
//
}
