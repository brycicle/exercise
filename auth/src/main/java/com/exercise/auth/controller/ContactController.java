package com.exercise.auth.controller;

import com.exercise.auth.dto.contact.ContactRequest;
import com.exercise.auth.dto.contact.ContactResponse;
import com.exercise.auth.service.ContactService;
import com.exercise.auth.util.response.ResponseCodesUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@Slf4j
public final class ContactController {
    private final ContactService service;

    @GetMapping("{id}")
    @ApiOperation(value = "Retrieves Account By ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved account",
                            response = ContactResponse.class
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.BAD_REQUEST,
                            message = "Validation Error"
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.UNAUTHORIZED,
                            message = "Unauthorized access"
                    )
            }
    )
    public ResponseEntity<ContactResponse> getContact(@PathVariable final String id) {
        log.info("getContact {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation(value = "Retrieves List of Contacts")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved list",
                            response = Page.class
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.BAD_REQUEST,
                            message = "Validation Error"
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.UNAUTHORIZED,
                            message = "Unauthorized access"
                    )
            }
    )
    public ResponseEntity<List<ContactResponse>> getContacts() {
        log.info("getContacts");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @ApiOperation(value = "Add Contact")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully added contact",
                            response = ContactResponse.class
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.BAD_REQUEST,
                            message = "Validation Error"
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.UNAUTHORIZED,
                            message = "Unauthorized access"
                    )
            }
    )
    public ResponseEntity<ContactResponse> addContact(@RequestBody final ContactRequest request) {
        log.info("addContact");
        return ResponseEntity.ok(service.createContact(request));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete a Contact")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully deleted contact",
                            response = ContactResponse.class
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.BAD_REQUEST,
                            message = "Validation Error"
                    ),
                    @ApiResponse(
                            code = ResponseCodesUtil.UNAUTHORIZED,
                            message = "Unauthorized access"
                    )
            }
    )
    public ResponseEntity<?> deleteContact(@PathVariable final String id) {
        log.info("deleteContact {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
