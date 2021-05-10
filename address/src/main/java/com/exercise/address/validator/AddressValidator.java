package com.exercise.address.validator;

import com.exercise.address.model.Address;
import com.exercise.address.util.validator.Validator;
import com.exercise.address.exceptions.FieldValidationException;
import com.exercise.address.util.validator.ParameterMap;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public final class AddressValidator extends Validator<Optional<Address>> {

    @Override
    protected Optional<Address> doValidate(final ParameterMap body) {
        final Address address = Optional.ofNullable(
                (Address) body.get("address")
        ).orElse(Address.builder().build());

        final Optional<Address> optional;

        validateFields(body);

        optional = Optional.of(build(address, body));

        return optional;
    }

    @Override
    @SneakyThrows
    protected void validateFields(final ParameterMap body) {
        body.validate("Type")
                .setException(new FieldValidationException("Type", "Required"))
                .required();

        body.validate("Number")
                .setException(new FieldValidationException("Number", "Required"))
                .isInt(new FieldValidationException("Number", "Should be a number"))
                .required();

        body.validate("Street")
                .setException(new FieldValidationException("Street", "Required"))
                .required();

        body.validate("Unit")
                .setException(new FieldValidationException("Unit", "Required"))
                .required();

        body.validate("City")
                .setException(new FieldValidationException("City", "Required"))
                .required();

        body.validate("State")
                .setException(new FieldValidationException("State", "Required"))
                .required();

        body.validate("Zipcode")
                .setException(new FieldValidationException("Zipcode", "Required"))
                .required();
    }

    protected Address build(final Address address, final ParameterMap body) {
        address.setType(body.getString("Type"));
        address.setNumber(Integer.parseInt(body.getString("Number")));
        address.setStreet(body.getString("Street"));
        address.setUnit(body.getString("Unit"));
        address.setCity(body.getString("City"));
        address.setState(body.getString("State"));
        address.setZipcode(body.getString("Zipcode"));

        return address;
    }
}
