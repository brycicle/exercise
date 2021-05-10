package com.exercise.auth.dto.account;

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
public class AccountRequest extends AccountJson {
    /**
     * To Parameter Map
     * @return ParameterMap of AccountJson
     */
    public ParameterMap toParameterMap() {
        final ParameterMap parameterMap = new ObjectMapper().convertValue(this, ParameterMap.class);

        parameterMap.putIfAbsent("Preferred", "false");
        return parameterMap;
    }

    /**
     * To Parameter Map for Updating
     * @return ParameterMap of AccountJson
     */
    public ParameterMap toParameterMap(final AccountRequest oldRequest) {
        return toParameterMap(this, oldRequest);
    }
}
