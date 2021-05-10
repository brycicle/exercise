package com.exercise.communication.service.impl;

import com.exercise.communication.dto.CommunicationRequest;
import com.exercise.communication.model.Communication;
import com.exercise.communication.repository.CommunicationRepository;
import com.exercise.communication.service.CommunicationService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext
public class CommunicationServiceImplTest {

    @Autowired
    private CommunicationService service;
    @MockBean
    private CommunicationRepository repository;

    private static String uuid = UUID.randomUUID().toString();

    @Before
    public void prepare() {
        Mockito
                .when(repository.findById(uuid))
                .thenReturn(Optional.of(createCommunication()));
        Mockito
                .when(repository.findAllByIdentificationIdOrderByPreferredDesc(any()))
                .thenReturn(List.of(createCommunication()));
        Mockito
                .when(repository.save(any(Communication.class)))
                .thenReturn(createCommunication());

    }

    public Communication createCommunication() {
        return Communication
                .builder()
                .id(UUID.randomUUID().toString())
                .type("Home")
                .value("4297")
                .createdAt(Instant.now())
                .build();
    }

    public CommunicationRequest createRequest() {
        CommunicationRequest addressRequest = new CommunicationRequest();
        addressRequest.setType("Home");
        addressRequest.setValue("1234");
        return addressRequest;
    }

    @Test
    public void successAddTest() {
        Assertions.assertDoesNotThrow(() -> service.save(UUID.randomUUID().toString(), List.of(createRequest())));
    }

    @Test
    public void failAddTestNullType() {
        CommunicationRequest request = createRequest();
        request.setType(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullValue() {
        CommunicationRequest request = createRequest();
        request.setValue(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
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
        Assertions.assertDoesNotThrow(() -> service.findAll(uuid));
    }

    @Test
    public void failNotAUuidTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.findById("NotAUUID"));
    }
}