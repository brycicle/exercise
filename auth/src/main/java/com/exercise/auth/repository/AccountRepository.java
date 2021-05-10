package com.exercise.auth.repository;

import com.exercise.auth.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
