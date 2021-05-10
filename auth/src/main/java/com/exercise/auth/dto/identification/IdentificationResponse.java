package com.exercise.auth.dto.identification;

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
public class IdentificationResponse extends IdentificationJson {
    @ApiModelProperty(notes = "Unique ID")
    private String id;
    @ApiModelProperty(notes = "Timestamp")
    private String createdAt;

    public IdentificationResponse(final Identification identification) {
        setId(identification.getId());
        setFirstName(identification.getFirstName());
        setLastName(identification.getLastName());
        setDateOfBirth(identification.getDateOfBirth().toString());
        setGender(Character.toString(identification.getGender()));
        setTitle(identification.getTitle());
        setCreatedAt(identification.getCreatedAt().toString());
    }
}
