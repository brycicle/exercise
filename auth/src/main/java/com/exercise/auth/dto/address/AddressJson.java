package com.exercise.auth.dto.address;

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
public class AddressJson extends JsonObject {
    @ApiModelProperty(notes = "Type", example = "Home")
    private String type;
    @ApiModelProperty(notes = "House/Lot Number", example = "1234")
    private String number;
    @ApiModelProperty(notes = "Street Address", example = "blah blah St.")
    private String street;
    @ApiModelProperty(notes = "Unit", example = "1 A")
    private String unit;
    @ApiModelProperty(notes = "City", example = "Somewhere")
    private String city;
    @ApiModelProperty(notes = "State", example = "WV")
    private String state;
    @ApiModelProperty(notes = "Zip Code", example = "12345")
    private String zipcode;
}
