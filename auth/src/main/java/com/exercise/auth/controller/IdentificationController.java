package com.exercise.auth.controller;

import com.exercise.auth.config.client.IdentificationClient;
import com.exercise.auth.dto.identification.Identification;
import com.exercise.auth.dto.identification.IdentificationRequest;
import com.exercise.auth.dto.identification.IdentificationResponse;
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
@RequestMapping("/identification")
@RequiredArgsConstructor
@Slf4j
public final class IdentificationController {
    private final IdentificationClient client;

    @GetMapping("{id}")
    @ApiOperation(value = "Retrieves Identification By ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved identification",
                            response = IdentificationResponse.class
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
    public ResponseEntity<IdentificationResponse> getIdentification(@PathVariable final String id) {
        log.info("getIdentification {}", id);
        return ResponseEntity.ok(client.getIdentification(id));
    }

    @GetMapping
    @ApiOperation(value = "Retrieves List of Identifications")
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
    public ResponseEntity<List<Identification>> getList() {
        log.info("getIdentificationList");
        return ResponseEntity.ok(client.getList());
    }

    @PostMapping
    @ApiOperation(value = "Saves an Identification")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.CREATED,
                            message = "Successfully saved identification",
                            response = IdentificationResponse.class
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
    public ResponseEntity<IdentificationResponse> addIdentification(@RequestBody final IdentificationRequest request) {
        log.info("addIdentification {}", request);
        return new ResponseEntity<>(client.addIdentification(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates an Identification")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully updated identification",
                            response = IdentificationResponse.class
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
    public ResponseEntity<IdentificationResponse> updateIdentification(
            @PathVariable final String id, @RequestBody final IdentificationRequest request
    ) {
        log.info("updateIdentification {} {}", id, request);
        return ResponseEntity.ok(client.updateIdentification(id, request));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes an Identification")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.NO_CONTENT,
                            message = "Successfully deleted an identification"
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
    public ResponseEntity<?> deleteIdentification(@PathVariable final String id) {
        log.info("deleteIdentification {}", id);
        client.deleteIdentification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

