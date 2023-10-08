package com.ruslan.DI;


import org.reflections.Reflections;

public interface ObjectConfigurator {
    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    Reflections getScanner();
}
