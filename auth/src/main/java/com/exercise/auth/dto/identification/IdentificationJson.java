package com.exercise.auth.dto.identification;

import com.exercise.auth.util.json.JsonObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class IdentificationJson extends JsonObject {
    @ApiModelProperty(notes = "First Name", example = "Bob")
    private String firstName;
    @ApiModelProperty(notes = "Last Name", example = "Fredrick")
    private String lastName;
    @ApiModelProperty(notes = "Date of Birth", example = "06/21/1980")
    private String dateOfBirth;
    @ApiModelProperty(notes = "Gender", example = "M")
    private String gender;
    @ApiModelProperty(notes = "Title", example = "Manager")
    private String title;
}
