package com.exercise.auth.dto.account;

import com.exercise.auth.model.Account;
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
public class AccountResponse extends AccountJson {
    @ApiModelProperty(notes = "Unique ID")
    private String id;

    public AccountResponse(final Account account) {
        setId(account.getId());
        setUsername(account.getUsername());
        setPassword(account.getPassword());
    }

}
