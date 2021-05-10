package com.exercise.auth.dto.account;

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
public class AccountJson extends JsonObject {
    @ApiModelProperty(notes = "Username", example = "user")
    private String username;
    @ApiModelProperty(notes = "Password", example = "pass")
    private String password;
}
