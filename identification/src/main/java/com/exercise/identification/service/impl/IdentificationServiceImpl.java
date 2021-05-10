package com.exercise.identification.service.impl;

import com.exercise.identification.dto.IdentificationRequest;
import com.exercise.identification.dto.IdentificationResponse;
import com.exercise.identification.exceptions.IdentificationAlreadyDoesNotExistException;
import com.exercise.identification.exceptions.IdentificationAlreadyExistsException;
import com.exercise.identification.exceptions.InvalidUuidException;
import com.exercise.identification.model.Identification;
import com.exercise.identification.repository.IdentificationRepository;
import com.exercise.identification.service.IdentificationService;
import com.exercise.identification.validator.IdentificationValidator;
import com.exercise.identification.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class IdentificationServiceImpl implements IdentificationService {

    private final IdentificationRepository repository;

    private final IdentificationValidator validator;

    @Override
    public IdentificationResponse save(final IdentificationRequest request) {
        Optional<Identification> identificationOptional = repository.findByFirstNameAndLastName(
                request.getFirstName(), request.getLastName()
        );

        if (identificationOptional.isEmpty()) {
            identificationOptional = validator.validate(request.toParameterMap());
        } else {
            throw new IdentificationAlreadyExistsException();
        }

        return new IdentificationResponse(repository.save(identificationOptional.get()));
    }

    @Override
    public IdentificationResponse update(final String id, final IdentificationRequest request) {
        isUuid(id);
        Optional<Identification> optional = repository.findById(id);
        final Identification oldIdentification;
        Identification identification = Identification.builder().build();

        if (optional.isPresent()) {
            oldIdentification = optional.get();
            optional = validator.validate(request.toParameterMap());
        } else {
            throw new IdentificationAlreadyDoesNotExistException();
        }

        if (optional.isPresent()) {
            identification = optional.get();

            identification.setId(oldIdentification.getId());
            identification.setCreatedAt(oldIdentification.getCreatedAt());
        }

        return new IdentificationResponse(repository.save(identification));
    }

    @Override
    public void delete(final String id) {
        isUuid(id);
        final Optional<Identification> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new IdentificationAlreadyDoesNotExistException();
        }
    }

    @Override
    public IdentificationResponse findById(final String id) {
        isUuid(id);
        final Optional<Identification> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new IdentificationAlreadyDoesNotExistException();
        }

        return new IdentificationResponse(optional.get());
    }

    @Override
    public List<Identification> findAll() {
        final List<Identification> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public void isUuid(final String id) {
        if (!Validator.isUuid(id)) {
            throw new InvalidUuidException();
        }
    }
}
