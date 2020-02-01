package com.mins.corona.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class CommonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Optional<String> objectToString(Object obj) {
        try {
            return Optional.of(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
