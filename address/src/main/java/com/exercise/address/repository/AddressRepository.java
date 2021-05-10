package com.exercise.address.repository;

import com.exercise.address.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findAllByIdentificationId(String identificationId);
}

