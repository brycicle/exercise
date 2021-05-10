package com.exercise.address.service;

import com.exercise.address.model.Address;
import com.exercise.address.dto.AddressRequest;
import com.exercise.address.dto.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> save(String identificationId, List<AddressRequest> request);

    AddressResponse update(String id, AddressRequest request);

    void delete(String id);

    AddressResponse findById(String id);

    List<Address> findAll(String identificationId);
}
