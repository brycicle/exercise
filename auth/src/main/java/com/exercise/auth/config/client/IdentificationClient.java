package com.exercise.auth.config.client;

import com.exercise.auth.dto.identification.Identification;
import com.exercise.auth.dto.identification.IdentificationRequest;
import com.exercise.auth.dto.identification.IdentificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "identification", url = "${IDENTIFICATION_SERVICE_HOST:http://localhost}:8001/api")
public interface IdentificationClient {

    @GetMapping("{id}")
    IdentificationResponse getIdentification(@PathVariable String id);

    @GetMapping
    List<Identification> getList();

    @PostMapping
    IdentificationResponse addIdentification(@RequestBody IdentificationRequest request);

    @PutMapping("{id}")
    IdentificationResponse updateIdentification(
            @PathVariable String id,
            @RequestBody IdentificationRequest request
    );

    @DeleteMapping("{id}")
    void deleteIdentification(@PathVariable String id);
}
