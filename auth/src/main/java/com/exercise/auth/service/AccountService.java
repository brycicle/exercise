package com.exercise.auth.service;

import com.exercise.auth.dto.account.AccountRequest;
import com.exercise.auth.dto.account.AccountResponse;
import com.exercise.auth.model.Account;

import java.util.List;

public interface AccountService {
    AccountResponse save(AccountRequest request);

    AccountResponse update(String id, AccountRequest request);

    void delete(String id);

    AccountResponse findById(String id);

    List<Account> findAll();
}
