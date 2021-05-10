package com.exercise.auth.dto.contact;

import com.exercise.auth.dto.address.AddressRequest;
import com.exercise.auth.dto.communication.CommunicationRequest;
import com.exercise.auth.dto.identification.IdentificationRequest;
import com.exercise.auth.util.json.JsonObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ContactJson extends JsonObject {
    private IdentificationRequest identification;

    @ApiModelProperty(notes = "List Of Address")
    private List<AddressRequest> address;

    @ApiModelProperty(notes = "List Of Communication")
    private List<CommunicationRequest> communication;
}
