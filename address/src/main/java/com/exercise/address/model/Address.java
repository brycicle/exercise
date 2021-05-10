package com.exercise.address.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Address {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private String id;

    private String identificationId;

    @Column(name = "typ")
    private String type;

    @Column(name = "num")
    private int number;

    private String street;

    private String unit;

    private String city;

    private String state;

    private String zipcode;

    private Instant createdAt;

    @PrePersist
    public final void onCreate() {
        setCreatedAt(Instant.now());
    }
}
