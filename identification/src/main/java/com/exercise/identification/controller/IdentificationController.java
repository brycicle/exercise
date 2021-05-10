package com.exercise.identification.controller;

import com.exercise.identification.dto.IdentificationRequest;
import com.exercise.identification.dto.IdentificationResponse;
import com.exercise.identification.model.Identification;
import com.exercise.identification.service.IdentificationService;
import com.exercise.identification.util.response.ResponseCodesUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public final class IdentificationController {
    private final IdentificationService service;
    private final Environment environment;

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
        return ResponseEntity.ok(service.findById(id));
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
    public ResponseEntity<List<Identification>> getList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") final int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") final int size
    ) {
        log.info(
                "Server Info : {} {}",
                environment.getProperty("HOSTNAME"),
                environment.getProperty("local.server.port")
        );
        log.info("getList {} {}", pageNumber, size);
        return ResponseEntity.ok(service.findAll());
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
        return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
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
        return ResponseEntity.ok(service.update(id, request));
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
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
