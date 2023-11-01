package com.ruslan.config;

import com.ruslan.DI.postProcessor.ObjectPostProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationPostProcessor implements ObjectPostProcessor {

    private final String DEFAULT_PATH_NAME = "config.properties";
    private final Logger logger = LogManager.getLogger();
    private final Map<String, Properties> mapProperties = new HashMap<>();

    public ConfigurationPostProcessor() {
        final Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(DEFAULT_PATH_NAME)));
            mapProperties.put(DEFAULT_PATH_NAME, properties);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
    }

    @Override
    public void process(final Object object) {
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
                if (this.mapProperties.containsKey(fileName)) {
                    Object value = this.mapProperties.get(fileName).get(propertyName);
                    if (value == null) {
                        return;
                    }
                    field.setAccessible(true);
                    field.set(object, castStringToType(configProperties, value.toString()));
                } else {
                    final Properties properties = new Properties();
                    properties.load(Files.newInputStream(Paths.get(fileName)));
                    this.mapProperties.put(fileName, properties);
                    final String propertyValue = properties.getProperty(configProperties.propertyName());
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
