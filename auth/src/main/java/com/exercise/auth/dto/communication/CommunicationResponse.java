package com.exercise.auth.dto.communication;

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
public class CommunicationResponse extends CommunicationJson {
    @ApiModelProperty(notes = "Unique ID")
    private String id;
    @ApiModelProperty(notes = "Timestamp")
    private String createdAt;

    public CommunicationResponse(final Communication communication) {
        setId(communication.getId());
        setType(communication.getType());
        setValue(communication.getValue());
        setPreferred(Boolean.toString(communication.isPreferred()));
        setCreatedAt(communication.getCreatedAt().toString());
    }
}
