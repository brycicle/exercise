package com.exercise.identification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Identification {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
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
