package com.exercise.auth.service.impl;

import com.exercise.auth.config.client.AddressClient;
import com.exercise.auth.config.client.CommunicationClient;
import com.exercise.auth.config.client.IdentificationClient;
import com.exercise.auth.dto.address.Address;
import com.exercise.auth.dto.address.AddressRequest;
import com.exercise.auth.dto.address.AddressRequestList;
import com.exercise.auth.dto.address.AddressResponse;
import com.exercise.auth.dto.communication.Communication;
import com.exercise.auth.dto.communication.CommunicationRequest;
import com.exercise.auth.dto.communication.CommunicationRequestList;
import com.exercise.auth.dto.communication.CommunicationResponse;
import com.exercise.auth.dto.contact.ContactRequest;
import com.exercise.auth.dto.identification.Identification;
import com.exercise.auth.dto.identification.IdentificationRequest;
import com.exercise.auth.dto.identification.IdentificationResponse;
import com.exercise.auth.service.ContactService;
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
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext
public class ContactServiceImplTest {

    @Autowired
    ContactService service;
    @MockBean
    private IdentificationClient identificationClient;
    @MockBean
    private AddressClient addressClient;
    @MockBean
    private CommunicationClient communicationClient;

    private static String identificationUuid = UUID.randomUUID().toString();

    @Before
    public void prepare() {
        Mockito
                .when(identificationClient.getList())
                .thenReturn(List.of(createIdentification()));
        Mockito
                .when(identificationClient.addIdentification(any(IdentificationRequest.class)))
                .thenReturn(new IdentificationResponse(createIdentification()));
        Mockito
                .when(identificationClient.getIdentification(identificationUuid))
                .thenReturn(new IdentificationResponse(createIdentification()));

        Mockito
                .when(addressClient.getList(identificationUuid))
                .thenReturn(List.of(createAddress()));
        Mockito
                .when(addressClient.addAddress(eq(identificationUuid), any(AddressRequestList.class)))
                .thenReturn(List.of(new AddressResponse(createAddress())));

        Mockito
                .when(communicationClient.getList(identificationUuid))
                .thenReturn(List.of(createCommunication()));
        Mockito
                .when(communicationClient.addCommunication(eq(identificationUuid), any(CommunicationRequestList.class)))
                .thenReturn(List.of(new CommunicationResponse(createCommunication())));
    }

    public Identification createIdentification() {
        final Identification identification = new Identification();
        identification.setId(UUID.randomUUID().toString());
        identification.setFirstName("FirstName");
        identification.setLastName("LastName");
        identification.setDateOfBirth(LocalDate.now());
        identification.setGender('M');
        identification.setTitle("TitleNew");
        identification.setCreatedAt(Instant.now());

        return identification;
    }

    public IdentificationRequest createIdentificationRequest() {
        IdentificationRequest identificationRequest = new IdentificationRequest();
        identificationRequest.setFirstName("FirstName");
        identificationRequest.setLastName("LastName");
        identificationRequest.setDateOfBirth("01/01/2020");
        identificationRequest.setGender("M");
        identificationRequest.setTitle("TitleNew");
        return identificationRequest;
    }

    public Address createAddress() {
        final Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setType("Home");
        address.setNumber(4297);
        address.setUnit("4A");
        address.setCity("SampleCity");
        address.setState("SampleState");
        address.setZipcode("SampleZipcode");
        address.setCreatedAt(Instant.now());
        return address;
    }

    public AddressRequest createAddressRequest() {
        final AddressRequest address = new AddressRequest();
        address.setType("Home");
        address.setNumber("4297");
        address.setUnit("4A");
        address.setCity("SampleCity");
        address.setState("SampleState");
        address.setZipcode("SampleZipcode");
        return address;
    }

    public Communication createCommunication() {
        final Communication communication = new Communication();
        communication.setId(UUID.randomUUID().toString());
        communication.setType("Home");
        communication.setValue("4297");
        communication.setCreatedAt(Instant.now());
        return communication;
    }

    public CommunicationRequest createCommunicationRequest() {
        final CommunicationRequest communication = new CommunicationRequest();
        communication.setType("Home");
        communication.setValue("4297");
        return communication;
    }

    public ContactRequest createContactRequest() {
        final ContactRequest contactRequest = new ContactRequest();
        contactRequest.setIdentification(createIdentificationRequest());
        contactRequest.setAddress(List.of(createAddressRequest()));
        contactRequest.setCommunication(List.of(createCommunicationRequest()));
        return contactRequest;
    }

    @Test
    public void getAll() {
        Assertions.assertDoesNotThrow(() -> service.getAll());
    }

    @Test
    public void createContact() {
        Assertions.assertDoesNotThrow(() -> service.createContact(createContactRequest()));
    }

    @Test
    public void findById() {
        Assertions.assertDoesNotThrow(() -> service.findById(identificationUuid));
    }

    @Test
    public void delete() {
        Assertions.assertDoesNotThrow(() -> service.delete(identificationUuid));
    }
}