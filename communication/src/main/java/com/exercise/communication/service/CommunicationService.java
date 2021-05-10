package com.exercise.communication.service;

import com.exercise.communication.dto.CommunicationRequest;
import com.exercise.communication.dto.CommunicationResponse;
import com.exercise.communication.model.Communication;

import java.util.List;

public interface CommunicationService {
    List<CommunicationResponse> save(String identificationId, List<CommunicationRequest> request);

    CommunicationResponse update(String id, CommunicationRequest request);

    void delete(String id);

    CommunicationResponse findById(String id);

    List<Communication> findAll(String identificationId);
}
