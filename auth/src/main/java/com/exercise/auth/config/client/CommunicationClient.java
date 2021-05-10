package com.exercise.auth.config.client;

import com.exercise.auth.dto.communication.Communication;
import com.exercise.auth.dto.communication.CommunicationRequest;
import com.exercise.auth.dto.communication.CommunicationRequestList;
import com.exercise.auth.dto.communication.CommunicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "communication", url = "${COMMUNICATION_SERVICE_HOST:http://localhost}:8003/api")
public interface CommunicationClient {

    @GetMapping("{id}")
    CommunicationResponse getCommunication(@PathVariable String id);

    @GetMapping("identification/{identificationId}")
    List<Communication> getList(@PathVariable String identificationId);

    @PostMapping
    List<CommunicationResponse> addCommunication(
            @RequestParam(value = "identificationId", required = false, defaultValue = "0")
            String identificationId,
            @RequestBody CommunicationRequestList request
    );

    @PutMapping("{id}")
    CommunicationResponse updateCommunication(
            @PathVariable String id,
            @RequestBody CommunicationRequest request
    );

    @DeleteMapping("{id}")
    void deleteCommunication(@PathVariable String id);
}
