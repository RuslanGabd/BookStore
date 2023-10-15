package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigurationProcessor {

    private static final String DEFAULT_PATH_NAME = "config.properties";
    private static final Logger logger = LogManager.getLogger();
    private final Properties properties;
    private final Map <String, Set<Map.Entry<Object, Object>>> mapProperties = new HashMap<>();
    public ConfigurationProcessor() {
        this.properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(DEFAULT_PATH_NAME)));
            mapProperties.put(DEFAULT_PATH_NAME, properties.entrySet());
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
    }

    public void configure(final Object object) {
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

            boolean accessible = field.canAccess(object);
            try {
                final String fileName = configProperties.configFileName();
                final String propertyName = configProperties.propertyName();
                if (DEFAULT_PATH_NAME.equals(fileName) || this.mapProperties.containsKey(fileName)) {
                    Object value = this.mapProperties.get(fileName).stream().filter(entry ->
                            propertyName.equals(entry.getKey())).findFirst().map(Map.Entry::getValue).orElse(null);
                    if (value == null) {
                        return;
                    }
                    field.setAccessible(true);
                    field.set(object, castStringToType(configProperties, value.toString()));
                } else {
                    this.properties.load(Files.newInputStream(Paths.get(fileName)));
                    this.mapProperties.put(fileName, this.properties.entrySet());
                    final String propertyValue = this.properties.getProperty(configProperties.propertyName());
                    field.setAccessible(true);
                    field.set(object, castStringToType(configProperties, propertyValue));
                }
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
