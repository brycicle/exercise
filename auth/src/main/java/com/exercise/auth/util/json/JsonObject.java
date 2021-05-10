package com.exercise.auth.util.json;

import com.exercise.auth.util.validator.ParameterMap;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class JsonObject {

    /**
     * Enters value to map if value from request JSON is not empty
     */
    public void putValue(
            final ParameterMap map, final String key, final String value
    ) {
        final Optional<String> optionalValue = Optional.ofNullable(value);
        map.put(key, optionalValue.orElse(null));
    }

    /**
     * Enters value to map if value from request JSON is not empty and enters the old value when field value is empty
     */
    public void putValue(
            final ParameterMap map, final String key, final String value, final Object oldValue
    ) {
        final Optional<String> optionalValue = Optional.ofNullable(value);
        map.put(key, optionalValue.orElse(String.valueOf(oldValue)));
    }

    /**
     * Enters value to map if value from request JSON is not empty and enters the old value when field value is empty
     */
    public ParameterMap toParameterMap(
            final Object newObject, final Object oldObject
    ) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final ParameterMap newMap = objectMapper.convertValue(newObject, ParameterMap.class);
        final ParameterMap oldMap = objectMapper.convertValue(oldObject, ParameterMap.class);
        final ParameterMap map = new ParameterMap();
        newMap.forEach((key, value) -> putValue(map, key, String.valueOf(value), oldMap.get(key))
        );
        return newMap;
    }
}
