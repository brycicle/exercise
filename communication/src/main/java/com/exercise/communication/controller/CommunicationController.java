package com.exercise.communication.controller;

import com.exercise.communication.dto.CommunicationRequest;
import com.exercise.communication.dto.CommunicationRequestList;
import com.exercise.communication.dto.CommunicationResponse;
import com.exercise.communication.model.Communication;
import com.exercise.communication.service.CommunicationService;
import com.exercise.communication.util.response.ResponseCodesUtil;
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
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public final class CommunicationController {
    private final CommunicationService service;

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
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("identification/{identificationId}")
    @ApiOperation(value = "Retrieves List of Communication")
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
        log.info("getList {}", identificationId);
        return ResponseEntity.ok(service.findAll(identificationId));
    }

    @PostMapping
    @ApiOperation(value = "Saves an Communication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.CREATED,
                            message = "Successfully saved communicationes",
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
        return new ResponseEntity<>(service.save(identificationId, request.getCommunication()), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates a Communication")
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
        return ResponseEntity.ok(service.update(id, request));
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
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
