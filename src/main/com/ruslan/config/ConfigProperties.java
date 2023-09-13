package com.ruslan.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class ConfigProperties {
    private final Path path = Paths.get("config.properties");

    private static final Logger logger = LogManager.getLogger();
    Properties properties = new Properties();


    public Integer getNumberMonthsOfStaleBooks() throws IOException {
        properties.load(Files.newInputStream(path));
        return Integer.parseInt(properties.getProperty("Number-Of-Months"));
    }

    public Boolean getAutoRequestsClosedIfBookAddStock() throws IOException {
        properties.load(Files.newInputStream(path));
        return Boolean.parseBoolean(properties.getProperty("Auto-Request-Closed"));
    }
}
