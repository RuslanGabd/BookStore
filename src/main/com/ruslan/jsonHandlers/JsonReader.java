package com.ruslan.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsonReader {
    private static JsonReader INSTANCE;

    public JsonReader() {
    }

    public static JsonReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonReader();
        }
        return INSTANCE;
    }

    public List readEntities(Class entityClass, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass));
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1));
        }

        return Collections.EMPTY_LIST;
    }
}
