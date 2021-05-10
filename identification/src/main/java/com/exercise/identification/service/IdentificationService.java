package com.exercise.identification.service;

import com.exercise.identification.dto.IdentificationRequest;
import com.exercise.identification.dto.IdentificationResponse;
import com.exercise.identification.model.Identification;

import java.util.List;

public interface IdentificationService {
    IdentificationResponse save(IdentificationRequest request);

    IdentificationResponse update(String id, IdentificationRequest request);

    void delete(String id);

    IdentificationResponse findById(String id);

    List<Identification> findAll();
}
