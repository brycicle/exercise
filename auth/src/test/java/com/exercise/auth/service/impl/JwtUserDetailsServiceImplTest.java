package com.exercise.auth.service.impl;

import com.exercise.auth.model.Account;
import com.exercise.auth.repository.AccountRepository;
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
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext
public class JwtUserDetailsServiceImplTest {

    @Autowired
    private JwtUserDetailsServiceImpl service;
    @MockBean
    private AccountRepository repository;

    @Before
    public void prepare() {
        Mockito
                .when(repository.findByUsername("user"))
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

    @Test
    public void successLoadTest() {
        Assertions.assertDoesNotThrow(() -> service.loadUserByUsername("user"));
    }

    @Test
    public void failAddDoesUsernameAlreadyExistTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.loadUserByUsername("doesNotExist"));
    }
}