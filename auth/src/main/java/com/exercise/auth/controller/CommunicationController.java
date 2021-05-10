package com.exercise.auth.controller;

import com.exercise.auth.config.client.CommunicationClient;
import com.exercise.auth.dto.communication.Communication;
import com.exercise.auth.dto.communication.CommunicationRequest;
import com.exercise.auth.dto.communication.CommunicationRequestList;
import com.exercise.auth.dto.communication.CommunicationResponse;
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
@RequestMapping("/communication")
@RequiredArgsConstructor
@Slf4j
public final class CommunicationController {
    private final CommunicationClient client;

    @GetMapping("{id}")
    @ApiOperation(value = "Retrieves Communication By ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved communication",
                            response = CommunicationResponse.class
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
    public ResponseEntity<CommunicationResponse> getCommunication(@PathVariable final String id) {
        log.info("getCommunication {}", id);
        return ResponseEntity.ok(client.getCommunication(id));
    }

    @GetMapping("identification/{identificationId}")
    @ApiOperation(value = "Retrieves List of Communications")
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
    public ResponseEntity<List<Communication>> getList(@PathVariable final String identificationId) {
        log.info("getCommunicationList");
        return ResponseEntity.ok(client.getList(identificationId));
    }

    @PostMapping
    @ApiOperation(value = "Saves an Communication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.CREATED,
                            message = "Successfully saved communication",
                            response = CommunicationResponse.class
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
    public ResponseEntity<List<CommunicationResponse>> addCommunication(
            @RequestParam(value = "identificationId", required = false, defaultValue = "0")
            final String identificationId,
            @RequestBody final CommunicationRequestList request
    ) {
        log.info("addCommunication {}", request);
        return new ResponseEntity<>(client.addCommunication(identificationId, request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates an Communication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully updated communication",
                            response = CommunicationResponse.class
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
    public ResponseEntity<CommunicationResponse> updateCommunication(
            @PathVariable final String id, @RequestBody final CommunicationRequest request
    ) {
        log.info("updateCommunication {} {}", id, request);
        return ResponseEntity.ok(client.updateCommunication(id, request));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes an Communication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.NO_CONTENT,
                            message = "Successfully deleted an communication"
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
    public ResponseEntity<?> deleteCommunication(@PathVariable final String id) {
        log.info("deleteCommunication {}", id);
        client.deleteCommunication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

