package com.exercise.auth.dto.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class AddressResponse extends AddressJson {
    @ApiModelProperty(notes = "Unique ID")
    private String id;
    @ApiModelProperty(notes = "Timestamp")
    private String createdAt;

    public AddressResponse(final Address address) {
        setId(address.getId());
        setType(address.getType());
        setNumber(Integer.toString(address.getNumber()));
        setStreet(address.getStreet());
        setUnit(address.getUnit());
        setCity(address.getCity());
        setState(address.getState());
        setZipcode(address.getZipcode());
        setCreatedAt(address.getCreatedAt().toString());
    }
}
