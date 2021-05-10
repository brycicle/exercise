package com.exercise.identification.validator;

import com.exercise.identification.exceptions.FieldValidationException;
import com.exercise.identification.model.Identification;
import com.exercise.identification.util.validator.ParameterMap;
import com.exercise.identification.util.validator.Validator;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public final class IdentificationValidator extends Validator<Optional<Identification>> {

    @Override
    protected Optional<Identification> doValidate(final ParameterMap body) {
        final Identification identification = Optional.ofNullable(
                (Identification) body.get("identification")
        ).orElse(Identification.builder().build());

        final Optional<Identification> optional;

        validateFields(body);

        optional = Optional.of(build(identification, body));

        return optional;
    }

    @Override
    @SneakyThrows
    protected void validateFields(final ParameterMap body) {
        body.validate("FirstName")
                .required();

        body.validate("LastName")
                .required();

        body.validate("DateOfBirth")
                .isDate(new FieldValidationException("DateOfBirth", "Invalid Date : " + body.getString("DateOfBirth")))
                .required();

        body.validate("Gender")
                .isGender(new FieldValidationException("Gender", "Invalid Gender"))
                .required();

        body.validate("Title")
                .required();
    }

    protected Identification build(final Identification identification, final ParameterMap body) {
        identification.setFirstName(body.getString("FirstName"));
        identification.setLastName(body.getString("LastName"));
        identification.setDateOfBirth(
                LocalDate.parse(body.getString("DateOfBirth"), DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        );
        identification.setGender(body.getString("Gender").charAt(0));
        identification.setTitle(body.getString("Title"));

        return identification;
    }
}
