package com.exercise.auth.service.impl;

import com.exercise.auth.config.exception.AccountAlreadyExistsException;
import com.exercise.auth.dto.account.AccountRequest;
import com.exercise.auth.dto.account.AccountResponse;
import com.exercise.auth.exceptions.AccountDoesNotExistException;
import com.exercise.auth.exceptions.InvalidUuidException;
import com.exercise.auth.model.Account;
import com.exercise.auth.repository.AccountRepository;
import com.exercise.auth.service.AccountService;
import com.exercise.auth.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final PasswordEncoder bcryptEncoder;

    @Override
    public AccountResponse save(final AccountRequest request) {
        final Optional<Account> accountOptional = repository.findByUsername(request.getUsername());
        final Account account = new Account();

        if (accountOptional.isEmpty()) {
            account.setUsername(request.getUsername());
            account.setPassword(bcryptEncoder.encode(request.getPassword()));
        } else {
            throw new AccountAlreadyExistsException("Account Already Exists");
        }

        return new AccountResponse(repository.save(account));
    }

    @Override
    public AccountResponse update(final String id, final AccountRequest request) {
        isUuid(id);

        final Optional<Account> optional = repository.findById(id);
        final Account oldAccount;
        final Account account = new Account();

        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new AccountAlreadyExistsException("Account Already Exists");
        }

        if (optional.isPresent()) {
            oldAccount = optional.get();
            account.setId(oldAccount.getId());
            account.setUsername(request.getUsername());
            account.setPassword(request .getPassword());
        } else {
            throw new AccountDoesNotExistException("Account Does Not Exist");
        }

        return new AccountResponse(repository.save(account));
    }

    @Override
    public void delete(final String id) {
        isUuid(id);
        final Optional<Account> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new AccountDoesNotExistException();
        }
    }

    @Override
    public AccountResponse findById(final String id) {
        isUuid(id);
        final Optional<Account> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new AccountDoesNotExistException();
        }

        return new AccountResponse(optional.get());
    }

    @Override
    public List<Account> findAll() {
        final List<Account> accounts = new ArrayList<>();
        repository.findAll().forEach(accounts::add);
        return accounts;
    }

    public void isUuid(final String id) {
        if (!Validator.isUuid(id)) {
            throw new InvalidUuidException();
        }
    }
}
