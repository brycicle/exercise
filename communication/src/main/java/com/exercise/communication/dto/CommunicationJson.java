package com.exercise.communication.dto;

import com.exercise.communication.util.json.JsonObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CommunicationJson extends JsonObject {
    @ApiModelProperty(notes = "Type", example = "email")
    private String type;
    @ApiModelProperty(notes = "Value", example = "bfe@sample.com")
    private String value;
    @ApiModelProperty(notes = "Preferred Communication", example = "blah blah St.")
    private String preferred;
}
