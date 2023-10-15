package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigPropertiesOld {
    private static final Logger logger = LogManager.getLogger();
    public static ConfigPropertiesOld INSTANCE = new ConfigPropertiesOld();

    private final Properties properties;

    ConfigPropertiesOld() {
        this.properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("config.properties")));
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
    }

    public Integer getIntProperty(String propertyName) {
        return Integer.parseInt(properties.getProperty(propertyName));
    }

    public Boolean getBooleanProperty(String propertyName) {
        return Boolean.parseBoolean(properties.getProperty(propertyName));
    }

    public static ConfigPropertiesOld getINSTANCE() {
        return INSTANCE;
    }
}
