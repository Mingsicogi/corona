package com.mins.corona.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Component
public class CommonUtil {

    @Value("${host}")
    private String host;

    private static StringBuilder hostBuilder;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Optional<String> objectToString(Object obj) {
        try {
            return Optional.of(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<Map<String, Object>> objectToMap(Object obj) {
        try {
            return Optional.of(objectMapper.convertValue(obj, Map.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static String getMakeUrl(String uri) {
        return hostBuilder.append(uri).toString();
    }

    @PostConstruct
    public void init() {
        hostBuilder = new StringBuilder(host);
    }
}
