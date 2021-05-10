package com.exercise.auth.service.impl;

import com.exercise.auth.config.client.AddressClient;
import com.exercise.auth.config.client.CommunicationClient;
import com.exercise.auth.config.client.IdentificationClient;
import com.exercise.auth.dto.address.AddressRequestList;
import com.exercise.auth.dto.address.AddressResponse;
import com.exercise.auth.dto.communication.CommunicationRequestList;
import com.exercise.auth.dto.communication.CommunicationResponse;
import com.exercise.auth.dto.contact.ContactRequest;
import com.exercise.auth.dto.contact.ContactResponse;
import com.exercise.auth.dto.identification.IdentificationResponse;
import com.exercise.auth.service.ContactService;
import com.exercise.auth.util.contact.ContactUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class ContactServiceImpl implements ContactService {

    private final IdentificationClient identificationClient;
    private final AddressClient addressClient;
    private final CommunicationClient communicationClient;

    @Override
    public List<ContactResponse> getAll() {
        final List<ContactResponse> contactResponseList = new ArrayList<>();
        identificationClient.getList().forEach(
            identification -> {
                final ContactResponse contactResponse = new ContactResponse();
                contactResponse.setIdentification(new IdentificationResponse(identification));
                final List<AddressResponse> addressResponseList = new ArrayList<>();
                addressClient.getList(identification.getId())
                        .forEach(address -> addressResponseList.add(new AddressResponse(address)));
                contactResponse.setAddress(addressResponseList);
                final List<CommunicationResponse> communicationResponseList = new ArrayList<>();
                communicationClient.getList(identification.getId()).forEach(
                    communication -> communicationResponseList
                            .add(new CommunicationResponse(communication))
                );
                contactResponse.setCommunication(communicationResponseList);
                contactResponseList.add(contactResponse);
            }
        );
        return contactResponseList;
    }

    @Override
    public ContactResponse findById(final String id) {
        final ContactResponse contactResponse = new ContactResponse();
        contactResponse.setIdentification(identificationClient.getIdentification(id));
        final List<AddressResponse> addressResponseList = new ArrayList<>();
        addressClient.getList(id).forEach(address -> addressResponseList.add(new AddressResponse(address)));
        contactResponse.setAddress(addressResponseList);
        final List<CommunicationResponse> communicationResponseList = new ArrayList<>();
        communicationClient.getList(id).forEach(
            communication -> communicationResponseList.add(new CommunicationResponse(communication))
        );
        contactResponse.setCommunication(communicationResponseList);
        return contactResponse;
    }

    @Override
    public void delete(final String id) {
        identificationClient.deleteIdentification(id);
        addressClient.getList(id).forEach(address -> addressClient.deleteAddress(address.getId()));
        communicationClient.getList(id).forEach(
            communication -> communicationClient.deleteCommunication(communication.getId())
        );
    }

    @Override
    @SneakyThrows
    public ContactResponse createContact(final ContactRequest request) {
        final ContactResponse contactResponse = new ContactResponse();
        Optional<String> identificationOptional = Optional.empty();
        Optional<List<String>> addressOptionals = Optional.empty();
        Optional<List<String>> communicationOptionals = Optional.empty();
        try {
            final IdentificationResponse identification = identificationClient.addIdentification(
                    request.getIdentification()
            );
            contactResponse.setIdentification(identification);

            identificationOptional = Optional.of(identification.getId());

            final AddressRequestList addressRequestList = new AddressRequestList();
            addressRequestList.setAddress(request.getAddress());
            contactResponse.setAddress(addressClient.addAddress(identification.getId(), addressRequestList));

            addressOptionals = Optional.of(ContactUtil.createAddressListId(contactResponse.getAddress()));

            final CommunicationRequestList communicationRequestList = new CommunicationRequestList();
            communicationRequestList.setCommunication(request.getCommunication());
            contactResponse.setCommunication(
                    communicationClient.addCommunication(identification.getId(), communicationRequestList)
            );

            communicationOptionals = Optional.of(
                    ContactUtil.createCommunicationListId(contactResponse.getCommunication())
            );

        } catch (final Exception exception) {
            exception.printStackTrace();
            revertSave(identificationOptional, addressOptionals, communicationOptionals);
        }
        return contactResponse;
    }

    public void revertSave(
            final Optional<String> identificationOptional,
            final Optional<List<String>> addressOptionals,
            final Optional<List<String>> communicationOptionals
    ) {
        identificationOptional.ifPresent(identificationClient::deleteIdentification);
        addressOptionals.ifPresent(strings -> strings.forEach(addressClient::deleteAddress));
        communicationOptionals.ifPresent(strings -> strings.forEach(communicationClient::deleteCommunication));
        throw new RuntimeException("Database Timeout");
    }
}
