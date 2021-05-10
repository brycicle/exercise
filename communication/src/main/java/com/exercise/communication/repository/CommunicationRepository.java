package com.exercise.communication.repository;

import com.exercise.communication.model.Communication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunicationRepository extends CrudRepository<Communication, String> {

    List<Communication> findAllByIdentificationIdOrderByPreferredDesc(String identificationId);
}

