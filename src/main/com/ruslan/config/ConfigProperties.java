package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class ConfigProperties {
    public static final ConfigProperties INSTANCE = new ConfigProperties();
    private final Path path = Paths.get("config.properties");

    private static final Logger logger = LogManager.getLogger();
    Properties properties = new Properties();


    public Integer getNumberMonthsOfStaleBooks() {
        try {
            properties.load(Files.newInputStream(path));
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
        return Integer.parseInt(properties.getProperty("number-of-months-to-mark-book-stale"));
    }

    public Boolean getAutoRequestsClosedIfBookAddStock() {
        try {
            properties.load(Files.newInputStream(path));
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
        return Boolean.parseBoolean(properties.getProperty("auto-request-closed-when-book-add-to-stock"));
    }
}
