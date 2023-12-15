package com.ruslan.DI.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperties {

    String configFileName() default "config.properties";

    String propertyName();

    Class type() default String.class;
}
