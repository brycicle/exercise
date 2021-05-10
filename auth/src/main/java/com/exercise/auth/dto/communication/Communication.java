package com.exercise.auth.dto.communication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Communication {
    private String id;

    private String identificationId;

    @Column(name = "typ")
    private String type;

    @Column(name = "val")
    private String value;

    private boolean preferred;

    private Instant createdAt;
}
