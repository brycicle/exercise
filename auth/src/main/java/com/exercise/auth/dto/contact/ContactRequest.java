package com.exercise.auth.dto.contact;

import com.exercise.auth.util.validator.ParameterMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ContactRequest extends ContactJson {
    /**
     * To Parameter Map
     * @return ParameterMap of AddressJson
     */
    public ParameterMap toParameterMap() {
        return new ObjectMapper().convertValue(this, ParameterMap.class);
    }

    /**
     * To Parameter Map for Updating
     * @return ParameterMap of AddressJson
     */
    public ParameterMap toParameterMap(final ContactRequest oldRequest) {
        return toParameterMap(this, oldRequest);
    }
}
