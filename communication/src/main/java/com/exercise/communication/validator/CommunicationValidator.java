package com.exercise.communication.validator;

import com.exercise.communication.exceptions.FieldValidationException;
import com.exercise.communication.model.Communication;
import com.exercise.communication.util.validator.ParameterMap;
import com.exercise.communication.util.validator.Validator;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public final class CommunicationValidator extends Validator<Optional<Communication>> {

    @Override
    protected Optional<Communication> doValidate(final ParameterMap body) {
        final Communication communication = Optional.ofNullable(
                (Communication) body.get("communication")
        ).orElse(Communication.builder().build());

        final Optional<Communication> optional;

        validateFields(body);

        optional = Optional.of(build(communication, body));

        return optional;
    }

    @Override
    @SneakyThrows
    protected void validateFields(final ParameterMap body) {
        body.validate("Type")
                .setException(new FieldValidationException("Type", "Required"))
                .required();

        body.validate("Value")
                .setException(new FieldValidationException("Value", "Required"))
                .required();

        body.validate("Preferred")
                .setException(new FieldValidationException("Preferred", "Required"))
                .isBoolean(new FieldValidationException("Preferred", "Invalid Value"))
                .required();
    }

    protected Communication build(final Communication communication, final ParameterMap body) {
        communication.setType(body.getString("Type"));
        communication.setValue(body.getString("Value"));
        communication.setPreferred(Boolean.parseBoolean(body.getString("Preferred")));

        return communication;
    }
}
