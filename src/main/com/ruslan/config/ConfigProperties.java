package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class ConfigProperties {
    private static final Logger logger = LogManager.getLogger();
    public static ConfigProperties INSTANCE = new ConfigProperties();

    final private Integer numberMonthsOfStaleBooks;
    final private Boolean autoRequestsClosedIfBookAddStock;

    ConfigProperties() {
        final Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("config.properties")));
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            logger.error("Something went wrong.", e);
            throw new RuntimeException();
        }
        numberMonthsOfStaleBooks = Integer.parseInt(properties.getProperty("number-of-months-to-mark-book-stale"));
        autoRequestsClosedIfBookAddStock = Boolean.parseBoolean(properties.getProperty("auto-request-closed-when-book-add-to-stock"));

    }

    public Integer getNumberMonthsOfStaleBooks() {
        return numberMonthsOfStaleBooks;
    }

    public Boolean getAutoRequestsClosedIfBookAddStock() {
        return autoRequestsClosedIfBookAddStock;
    }

    public static ConfigProperties getINSTANCE() {
        return INSTANCE;
    }
}
