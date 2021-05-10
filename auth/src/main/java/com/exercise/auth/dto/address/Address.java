package com.exercise.auth.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String id;

    private String identificationId;

    private String type;

    private int number;

    private String street;

    private String unit;

    private String city;

    private String state;

    private String zipcode;

    private Instant createdAt;
}
