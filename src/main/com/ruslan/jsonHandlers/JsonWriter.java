package com.ruslan.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    private static JsonWriter INSTANCE;

    public JsonWriter() {
    }

    public static JsonWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonWriter();
        }
        return INSTANCE;
    }

    public void writeEntities(List entities, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new FileWriter(path), entities);
        } catch (IOException e) {
            System.out.println("Exception occurred during writing into file " + path.substring(path.lastIndexOf("\\") + 1));
        }
    }


}