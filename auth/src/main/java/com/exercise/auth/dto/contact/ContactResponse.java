package com.exercise.auth.dto.contact;

import com.exercise.auth.dto.address.AddressResponse;
import com.exercise.auth.dto.communication.CommunicationResponse;
import com.exercise.auth.dto.identification.IdentificationResponse;
import com.exercise.auth.util.json.JsonObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ContactResponse extends JsonObject {
    private IdentificationResponse identification;

    private List<AddressResponse> address;

    private List<CommunicationResponse> communication;
}
