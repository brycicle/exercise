package com.exercise.auth.util.contact;

import com.exercise.auth.dto.address.AddressResponse;
import com.exercise.auth.dto.communication.CommunicationResponse;
import java.util.ArrayList;
import java.util.List;

public final class ContactUtil {

    private ContactUtil() { }

    public static List<String> createAddressListId(final List<AddressResponse> addressResponseList) {
        final List<String> addressListId = new ArrayList<>();
        for (final AddressResponse addressResponse : addressResponseList) {
            addressListId.add(addressResponse.getId());
        }
        return addressListId;
    }

    public static List<String> createCommunicationListId(final List<CommunicationResponse> communicationResponses) {
        final List<String> communicationListId = new ArrayList<>();
        for (final CommunicationResponse communicationResponse : communicationResponses) {
            communicationListId.add(communicationResponse.getId());
        }
        return communicationListId;
    }
}
