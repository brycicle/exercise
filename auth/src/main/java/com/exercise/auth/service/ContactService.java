package com.exercise.auth.service;

import com.exercise.auth.dto.contact.ContactRequest;
import com.exercise.auth.dto.contact.ContactResponse;

import java.util.List;

public interface ContactService {
    List<ContactResponse> getAll();
    ContactResponse createContact(ContactRequest request);
    ContactResponse findById(String id);
    void delete(String id);
}
