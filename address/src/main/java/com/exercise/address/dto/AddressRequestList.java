package com.exercise.address.dto;

import com.exercise.address.util.json.JsonObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class AddressRequestList extends JsonObject {
    @ApiModelProperty(notes = "List Of Address", example = "Home")
    private List<AddressRequest> address;
}
