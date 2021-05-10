package com.exercise.auth.controller;

import com.exercise.auth.config.client.AddressClient;
import com.exercise.auth.dto.address.Address;
import com.exercise.auth.dto.address.AddressRequest;
import com.exercise.auth.dto.address.AddressRequestList;
import com.exercise.auth.dto.address.AddressResponse;
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
@RequestMapping("/address")
@RequiredArgsConstructor
@Slf4j
public final class AddressController {
    private final AddressClient client;

    @GetMapping("{id}")
    @ApiOperation(value = "Retrieves Address By ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved address",
                            response = AddressResponse.class
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
    public ResponseEntity<AddressResponse> getAddress(@PathVariable final String id) {
        log.info("getAddress {}", id);
        return ResponseEntity.ok(client.getAddress(id));
    }

    @GetMapping("identification/{identificationId}")
    @ApiOperation(value = "Retrieves List of Addresss")
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
    public ResponseEntity<List<Address>> getList(@PathVariable final String identificationId) {
        log.info("getAddressList");
        return ResponseEntity.ok(client.getList(identificationId));
    }

    @PostMapping
    @ApiOperation(value = "Saves an Address")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.CREATED,
                            message = "Successfully saved address",
                            response = AddressResponse.class
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
    public ResponseEntity<List<AddressResponse>> addAddress(
            @RequestParam(value = "identificationId", required = false, defaultValue = "0")
            final String identificationId,
            @RequestBody final AddressRequestList request
    ) {
        log.info("addAddress {}", request);
        return new ResponseEntity<>(client.addAddress(identificationId, request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates an Address")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully updated address",
                            response = AddressResponse.class
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
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable final String id, @RequestBody final AddressRequest request
    ) {
        log.info("updateAddress {} {}", id, request);
        return ResponseEntity.ok(client.updateAddress(id, request));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes an Address")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.NO_CONTENT,
                            message = "Successfully deleted an address"
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
    public ResponseEntity<?> deleteAddress(@PathVariable final String id) {
        log.info("deleteAddress {}", id);
        client.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

