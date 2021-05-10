package com.exercise.identification.repository;

import com.exercise.identification.model.Identification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentificationRepository extends CrudRepository<Identification, String> {

    Optional<Identification> findByFirstNameAndLastName(String firstName, String lastName);

    Page<Identification> findAll(Pageable pageable);
}

