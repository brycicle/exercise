package com.exercise.identification.service.impl;

import com.exercise.identification.dto.IdentificationRequest;
import com.exercise.identification.model.Identification;
import com.exercise.identification.repository.IdentificationRepository;
import com.exercise.identification.service.IdentificationService;
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
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext
public class IdentificationServiceImplTest {

    @Autowired
    private IdentificationService service;
    @MockBean
    private IdentificationRepository repository;

    private static String uuid = UUID.randomUUID().toString();

    @Before
    public void prepare() {
        Mockito
                .when(repository.findById(uuid))
                .thenReturn(Optional.of(createIdentification()));
        Mockito
                .when(repository.findAll())
                .thenReturn(List.of(createIdentification()));
        Mockito
                .when(repository.save(any(Identification.class)))
                .thenReturn(createIdentification());
    }

    public Identification createIdentification() {
        return Identification
                .builder()
                .id(UUID.randomUUID().toString())
                .firstName("First")
                .lastName("Last")
                .dateOfBirth(LocalDate.now())
                .gender('M')
                .title("Title")
                .createdAt(Instant.now())
                .build();
    }

    public IdentificationRequest createRequest() {
        IdentificationRequest identificationRequest = new IdentificationRequest();
        identificationRequest.setFirstName("FirstName");
        identificationRequest.setLastName("LastName");
        identificationRequest.setDateOfBirth("01/01/2020");
        identificationRequest.setGender("M");
        identificationRequest.setTitle("TitleNew");
        return identificationRequest;
    }

    @Test
    public void successAddTest() {
        Assertions.assertDoesNotThrow(() -> service.save(createRequest()));
    }

    @Test
    public void failAddTestNullFirstName() {
        IdentificationRequest request = createRequest();
        request.setFirstName(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(request));
    }

    @Test
    public void failAddTestNullLastName() {
        IdentificationRequest request = createRequest();
        request.setLastName(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(request));
    }

    @Test
    public void failAddTestNullDateOfBirth() {
        IdentificationRequest request = createRequest();
        request.setDateOfBirth(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(request));
    }

    @Test
    public void failAddTestNullGender() {
        IdentificationRequest request = createRequest();
        request.setGender(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(request));
    }

    @Test
    public void failAddTestNullTitle() {
        IdentificationRequest request = createRequest();
        request.setTitle(null);
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