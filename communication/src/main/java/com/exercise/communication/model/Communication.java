package com.exercise.communication.model;

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
public class Communication {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private String id;

    private String identificationId;

    @Column(name = "typ")
    private String type;

    @Column(name = "val")
    private String value;

    private boolean preferred;

    private Instant createdAt;

    @PrePersist
    public final void onCreate() {
        setCreatedAt(Instant.now());
    }
}
