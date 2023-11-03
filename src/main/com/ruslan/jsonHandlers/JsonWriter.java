package com.ruslan.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    private final Logger logger = LogManager.getLogger();
    private static JsonWriter INSTANCE;

    ObjectMapper objectMapper;

    private JsonWriter() {
        this.objectMapper = new ObjectMapper();
    }

    public static JsonWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonWriter();
        }
        return INSTANCE;
    }

    public void writeEntities(List entities, String path) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new FileWriter(path), entities);
        } catch (IOException e) {
            System.out.println("Could not write data to file " + path.substring(path.lastIndexOf("\\") + 1));
            logger.error("Could not write data from file", e);
        }
    }


}