package com.exercise.auth.controller;

import com.exercise.auth.dto.account.AccountRequest;
import com.exercise.auth.dto.account.AccountResponse;
import com.exercise.auth.model.Account;
import com.exercise.auth.service.AccountService;
import com.exercise.auth.util.response.ResponseCodesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public final class AccountController {
    private final AccountService service;

    @GetMapping("{id}")
    @ApiOperation(value = "Retrieves Account By ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully retrieved account",
                            response = AccountResponse.class
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
    public ResponseEntity<AccountResponse> getAccount(@PathVariable final String id) {
        log.info("getAccount {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation(value = "Retrieves List of Account")
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
    public ResponseEntity<List<Account>> getList() {
        log.info("getList");
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("register")
    @ApiOperation(value = "Saves an Account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.CREATED,
                            message = "Successfully saved accounts",
                            response = AccountResponse.class
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
    @SneakyThrows
    public ResponseEntity<AccountResponse> addAccount(@RequestBody final AccountRequest request) {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
        log.info("addAccount {}", request);
        return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates a Account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.OK,
                            message = "Successfully updated account",
                            response = AccountResponse.class
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
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable final String id, @RequestBody final AccountRequest request
    ) {
        log.info("updateAccount {} {}", id, request);
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes an Account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = ResponseCodesUtil.NO_CONTENT,
                            message = "Successfully deleted an account"
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
    public ResponseEntity<?> deleteAccount(@PathVariable final String id) {
        log.info("deleteAccount {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
