package com.exercise.address.service.impl;

import com.exercise.address.dto.AddressRequest;
import com.exercise.address.dto.AddressResponse;
import com.exercise.address.exceptions.AddressDoesNotExistException;
import com.exercise.address.exceptions.InvalidUuidException;
import com.exercise.address.model.Address;
import com.exercise.address.repository.AddressRepository;
import com.exercise.address.service.AddressService;
import com.exercise.address.util.validator.Validator;
import com.exercise.address.validator.AddressValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final AddressValidator validator;

    @Override
    public List<AddressResponse> save(final String identificationId, final List<AddressRequest> request) {
        final List<AddressResponse> list = new ArrayList<>();
        request.forEach(
            addressRequest -> {
                final Optional<Address> optional = validator.validate(addressRequest.toParameterMap());
                Address address = Address.builder().build();

                if (optional.isPresent()) {
                    address = optional.get();
                    address.setIdentificationId(identificationId);
                    list.add(new AddressResponse(repository.save(address)));
                }
            }
        );

        return list;
    }

    @Override
    public AddressResponse update(final String id, final AddressRequest request) {
        isUuid(id);
        Optional<Address> optional = repository.findById(id);
        final Address oldAddress;
        Address address = Address.builder().build();

        if (optional.isPresent()) {
            oldAddress = optional.get();
            optional = validator.validate(request.toParameterMap());
        } else {
            throw new AddressDoesNotExistException();
        }

        if (optional.isPresent()) {
            address = optional.get();

            address.setId(oldAddress.getId());
            address.setCreatedAt(oldAddress.getCreatedAt());
            address.setIdentificationId(oldAddress.getIdentificationId());
        }

        return new AddressResponse(repository.save(address));
    }

    @Override
    public void delete(final String id) {
        isUuid(id);
        final Optional<Address> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new AddressDoesNotExistException();
        }
    }

    @Override
    public AddressResponse findById(final String id) {
        isUuid(id);
        final Optional<Address> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new AddressDoesNotExistException();
        }

        return new AddressResponse(optional.get());
    }

    @Override
    public List<Address> findAll(final String identificationId) {
        return repository.findAllByIdentificationId(identificationId);
    }

    public void isUuid(final String id) {
        if (!Validator.isUuid(id)) {
            throw new InvalidUuidException();
        }
    }
}
