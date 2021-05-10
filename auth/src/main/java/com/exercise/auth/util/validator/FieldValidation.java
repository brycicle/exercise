package com.exercise.auth.util.validator;

import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public final class FieldValidation {

    private String fieldName;
    private String fieldValue;
    private ParameterMap parameterMap;

    public String getValue() {
        return this.fieldValue;
    }

    public FieldValidation isUuid() {
        return isUuid(parameterMap.getException());
    }

    public FieldValidation isUuid(final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isUuid(value);
            }
            return condition;
        }, exception);
    }

    public FieldValidation isInt() {
        return isInt(parameterMap.getException());
    }

    public FieldValidation isInt(final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isInt(value);
            }
            return condition;
        }, exception);
    }

    public FieldValidation isDouble() {
        return isDouble(parameterMap.getException());
    }

    public FieldValidation isDouble(final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isDouble(value);
            }
            return condition;
        }, exception);
    }

    public FieldValidation isLong() {
        return isLong(parameterMap.getException());
    }

    public FieldValidation isLong(final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isLong(value);
            }
            return condition;
        }, exception);
    }

    public FieldValidation isBoolean() {
        return isBoolean(parameterMap.getException());
    }

    public FieldValidation isBoolean(final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value)) {
                condition = false;
            }
            condition = Validator.isBoolean(value);
            return condition;
        }, exception);
    }

    public FieldValidation required() {
        return required(parameterMap.getException());
    }

    public FieldValidation required(final RuntimeException exception) {
        return condition(value -> {
            return !Validator.isEmpty(value);
        }, exception);
    }

    public FieldValidation minLength(final int length) {
        return minLength(length, parameterMap.getException());
    }

    public FieldValidation minLength(final int length, final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isMinLength(value, length);
            }
            return condition;
        }, exception);
    }

    public FieldValidation maxLength(final int length) {
        return maxLength(length, parameterMap.getException());
    }

    public FieldValidation maxLength(final int length, final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else {
                condition = Validator.isMaxLength(value, length);
            }
            return condition;
        }, exception);
    }

    public FieldValidation minValue(final double minValue) {
        return minValue(minValue, parameterMap.getException());
    }

    public FieldValidation minValue(final double minValue, final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value) || Validator.isNull(value)) {
                condition = false;
            } else if (!Validator.isNumber(fieldValue)) {
                condition = false;
            } else {
                condition = Validator.isMinValue(fieldValue, minValue);
            }
            return condition;
        }, exception);
    }

    public FieldValidation maxValue(final double maxValue) {
        return maxValue(maxValue, parameterMap.getException());
    }

    public FieldValidation maxValue(final double maxValue, final RuntimeException exception) {
        return condition(value -> {
            boolean condition = false;
            if (Validator.isEmpty(value)) {
                condition = false;
            } else if (!Validator.isNumber(fieldValue)) {
                condition = false;
            } else {
                condition = Validator.isMaxValue(fieldValue, maxValue);
            }
            return condition;
        }, exception);
    }

    public FieldValidation setException(final RuntimeException exception) {
        parameterMap.setException(exception);
        return Optional.of(this).get();
    }

    public FieldValidation condition(final BooleanCondition condition) {
        return condition(condition, this.parameterMap.getException(), new Object[] {});
    }

    public FieldValidation condition(final BooleanCondition condition, final RuntimeException exception) {
        return condition(condition, exception, new Object[] {});
    }

    public FieldValidation condition(
            final BooleanCondition condition,
            final RuntimeException exception,
            final Object... arguments
    ) {
        final Optional<FieldValidation> fieldValidation = Optional.of(this);

        if (!condition.isTrue(fieldValue)) {
            parameterMap.reject(fieldName, exception, arguments);
        }

        return fieldValidation.get();
    }
}
