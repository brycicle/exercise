package com.exercise.auth.dto.identification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private char gender;

    private String title;

    private Instant createdAt;

    @PrePersist
    public final void onCreate() {
        setCreatedAt(Instant.now());
    }
}
