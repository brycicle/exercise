package com.exercise.auth.service.impl;

import com.exercise.auth.dto.account.AccountRequest;
import com.exercise.auth.model.Account;
import com.exercise.auth.repository.AccountRepository;
import com.exercise.auth.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext
public class AccountServiceImplTest {

    @Autowired
    private AccountService service;
    @MockBean
    private AccountRepository repository;

    private static String uuid = UUID.randomUUID().toString();

    @Before
    public void prepare() {
        Mockito
                .when(repository.findById(uuid))
                .thenReturn(Optional.of(createAccount()));
        Mockito
                .when(repository.findAll())
                .thenReturn(List.of(createAccount()));
        Mockito
                .when(repository.save(any(Account.class)))
                .thenReturn(createAccount());
        Mockito
                .when(repository.findByUsername("Exist"))
                .thenReturn(Optional.of(createAccount()));
    }

    public Account createAccount() {
        return Account
                .builder()
                .id(UUID.randomUUID().toString())
                .username("user")
                .password("Last")
                .build();
    }

    public AccountRequest createRequest() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUsername("username");
        accountRequest.setPassword("password");
        return accountRequest;
    }

    @Test
    public void successAddTest() {
        Assertions.assertDoesNotThrow(() -> service.save(createRequest()));
    }

    @Test
    public void failAddDoesUsernameAlreadyExistTest() {
        AccountRequest request = createRequest();
        request.setUsername("Exist");
        Assertions.assertThrows(RuntimeException.class, () -> service.save(request));
    }

    @Test
    public void successUpdateTest() {
        Assertions.assertDoesNotThrow(() -> service.update(uuid, createRequest()));
    }

    @Test
    public void failUpdateDoesNotExistTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.update(UUID.randomUUID().toString(), createRequest()));
    }

    @Test
    public void failUpdateDoesUsernameAlreadyExistTest() {
        AccountRequest request = createRequest();
        request.setUsername("Exist");
        Assertions.assertThrows(RuntimeException.class, () -> service.update(uuid, request));
    }

    @Test
    public void successDeleteTest() {
        Assertions.assertDoesNotThrow(() -> service.delete(uuid));
    }

    @Test
    public void failDeleteDoesNotExistTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.delete(UUID.randomUUID().toString()));
    }

    @Test
    public void successFindByIdTest() {
        Assertions.assertDoesNotThrow(() -> service.findById(uuid));
    }

    @Test
    public void failFindByIdDoesNotExistTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.findById(UUID.randomUUID().toString()));
    }

    @Test
    public void successFindAllTest() {
        Assertions.assertDoesNotThrow(() -> service.findAll());
    }

    @Test
    public void failNotAUuidTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.findById("NotAUUID"));
    }
}