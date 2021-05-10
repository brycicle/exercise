package com.exercise.address.service.impl;

import com.exercise.address.dto.AddressRequest;
import com.exercise.address.model.Address;
import com.exercise.address.repository.AddressRepository;
import com.exercise.address.service.AddressService;
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
public class AddressServiceImplTest {

    @Autowired
    private AddressService service;
    @MockBean
    private AddressRepository repository;

    private static String uuid = UUID.randomUUID().toString();

    @Before
    public void prepare() {
        Mockito
                .when(repository.findById(uuid))
                .thenReturn(Optional.of(createAddress()));
        Mockito
                .when(repository.findAllByIdentificationId(any()))
                .thenReturn(List.of(createAddress()));
        Mockito
                .when(repository.save(any(Address.class)))
                .thenReturn(createAddress());

    }

    public Address createAddress() {
        return Address
                .builder()
                .id(UUID.randomUUID().toString())
                .type("Home")
                .number(4297)
                .street("Sample St.")
                .unit("4A")
                .city("SampleCity")
                .state("SampleState")
                .zipcode("SampleZipcode")
                .createdAt(Instant.now())
                .build();
    }

    public AddressRequest createRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setType("Home");
        addressRequest.setNumber("1234");
        addressRequest.setStreet("Street");
        addressRequest.setUnit("1A");
        addressRequest.setCity("City");
        addressRequest.setState("State");
        addressRequest.setZipcode("12345");
        return addressRequest;
    }

    @Test
    public void successAddTest() {
        Assertions.assertDoesNotThrow(() -> service.save(UUID.randomUUID().toString(), List.of(createRequest())));
    }

    @Test
    public void failAddTestNullType() {
        AddressRequest request = createRequest();
        request.setType(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullNumber() {
        AddressRequest request = createRequest();
        request.setNumber(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullStreet() {
        AddressRequest request = createRequest();
        request.setStreet(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullUnit() {
        AddressRequest request = createRequest();
        request.setUnit(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullCity() {
        AddressRequest request = createRequest();
        request.setCity(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullState() {
        AddressRequest request = createRequest();
        request.setState(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNullZipcode() {
        AddressRequest request = createRequest();
        request.setZipcode(null);
        Assertions.assertThrows(RuntimeException.class, () -> service.save(UUID.randomUUID().toString(), List.of(request)));
    }

    @Test
    public void failAddTestNonIntNumber() {
        AddressRequest request = createRequest();
        request.setNumber("NotANumber");
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