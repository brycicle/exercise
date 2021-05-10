package com.exercise.communication.service.impl;

import com.exercise.communication.dto.CommunicationRequest;
import com.exercise.communication.dto.CommunicationResponse;
import com.exercise.communication.exceptions.CommunicationDoesNotExistException;
import com.exercise.communication.exceptions.InvalidUuidException;
import com.exercise.communication.model.Communication;
import com.exercise.communication.repository.CommunicationRepository;
import com.exercise.communication.service.CommunicationService;
import com.exercise.communication.util.validator.Validator;
import com.exercise.communication.validator.CommunicationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationRepository repository;

    private final CommunicationValidator validator;

    @Override
    public List<CommunicationResponse> save(final String identificationId, final List<CommunicationRequest> request) {
        final List<CommunicationResponse> list = new ArrayList<>();
        request.forEach(
            communicationRequest -> {
                final Optional<Communication> optional = validator.validate(communicationRequest.toParameterMap());
                Communication communication = Communication.builder().build();

                if (optional.isPresent()) {
                    communication = optional.get();
                    communication.setIdentificationId(identificationId);
                    list.add(new CommunicationResponse(repository.save(communication)));
                }
            }
        );

        return list;
    }

    @Override
    public CommunicationResponse update(final String id, final CommunicationRequest request) {
        isUuid(id);
        Optional<Communication> optional = repository.findById(id);
        final Communication oldCommunication;
        Communication communication = Communication.builder().build();

        if (optional.isPresent()) {
            oldCommunication = optional.get();
            optional = validator.validate(request.toParameterMap());
        } else {
            throw new CommunicationDoesNotExistException();
        }

        if (optional.isPresent()) {
            communication = optional.get();
            communication.setIdentificationId(oldCommunication.getIdentificationId());
            communication.setId(oldCommunication.getId());
            communication.setCreatedAt(oldCommunication.getCreatedAt());
        }

        return new CommunicationResponse(repository.save(communication));
    }

    @Override
    public void delete(final String id) {
        isUuid(id);
        final Optional<Communication> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new CommunicationDoesNotExistException();
        }
    }

    @Override
    public CommunicationResponse findById(final String id) {
        isUuid(id);
        final Optional<Communication> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new CommunicationDoesNotExistException();
        }

        return new CommunicationResponse(optional.get());
    }

    @Override
    public List<Communication> findAll(final String identificationId) {
        return repository.findAllByIdentificationIdOrderByPreferredDesc(identificationId);
    }

    public void isUuid(final String id) {
        if (!Validator.isUuid(id)) {
            throw new InvalidUuidException();
        }
    }
}
