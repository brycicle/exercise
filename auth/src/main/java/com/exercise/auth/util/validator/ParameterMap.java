package com.exercise.auth.util.validator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Data
public class ParameterMap extends HashMap<String, Object> {

    private RuntimeException exception;

    /**
     * Returns the String used on validator
     * @return The String used on validator
     */
    public String getString(final String key) {
        final Optional<Object> val = Optional.ofNullable(getOrDefault(key, null));
        String stringValue = "";

        if (!(val.get() instanceof String)) {
            stringValue = val.get().toString();
            if (val.isEmpty()) {
                stringValue = "";
            }
        } else {
            stringValue = String.valueOf(val.get());
        }

        return val.isEmpty() ? null : stringValue;
    }

    /** Reject
     * @param field FieldName to be rejected
     * @param exception Exception to be thrown
     */
    public void reject(final String field, final RuntimeException exception) {
        reject(field, exception, new Object[] {});
    }

    /** Reject
     * @param field FieldName to be rejected
     * @param exception Exception to be thrown
     */
    public void reject(final String field, final RuntimeException exception, final Object... arguments) {
        log.info("Request Field Exception : {}", field);
        throw exception;
    }

    /** Calls the constructor of FieldValidation
     * @return FieldValidation object used to validate fields
     */
    public FieldValidation validate(final String fieldName) {
        setException(new RuntimeException());
        return new FieldValidation(fieldName, getString(fieldName), this);
    }

    /** Calls the equals of ConcurrentHashMap
     * @return Boolean value if the compared object is equal
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (super.equals(obj)) {
            result = true;
        }
        return result;
    }

    /** Calls the hashCode of ConcurrentHashMap
     * @return Hashcode value
     */
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        return hashCode;
    }
}
