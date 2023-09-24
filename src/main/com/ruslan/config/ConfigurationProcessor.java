package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigurationProcessor {
    private static final Logger logger = LogManager.getLogger();
    public static void configure(final Object object) {
        final Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(Configuration.class)) {
            return;
        }

        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ConfigProperties.class)) {
                continue;
            }
            ConfigProperties configProperties = field.getAnnotation(ConfigProperties.class);
            final Properties properties = new Properties();

            boolean accessible = field.canAccess(object);
            try {
                properties.load(Files.newInputStream(Paths.get(configProperties.configFileName())));
                final String propertyValue = properties.getProperty(configProperties.propertyName());
                field.setAccessible(true);
                field.set(object, castStringToType(configProperties, propertyValue));
            } catch (IllegalAccessException | IOException e) {
                logger.error("Something went wrong", e);
                throw new RuntimeException(e);
            }
            field.setAccessible(accessible);
        }
    }

    private static Object castStringToType(ConfigProperties configProperties, String string) {

        if (configProperties.type().equals(Integer.class)) {
            return Integer.parseInt(string);
        } else if (configProperties.type().equals(Boolean.class)) {
            return Boolean.parseBoolean(string);
        } else {
            return string;
        }
    }
}
