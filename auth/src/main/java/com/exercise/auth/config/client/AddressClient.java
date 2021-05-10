package com.exercise.auth.config.client;

import com.exercise.auth.dto.address.Address;
import com.exercise.auth.dto.address.AddressRequest;
import com.exercise.auth.dto.address.AddressRequestList;
import com.exercise.auth.dto.address.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "address", url = "${ADDRESS_SERVICE_HOST:http://localhost}:8002/api")
public interface AddressClient {

    @GetMapping("{id}")
    AddressResponse getAddress(@PathVariable String id);

    @GetMapping("identification/{identificationId}")
    List<Address> getList(@PathVariable String identificationId);

    @PostMapping
    List<AddressResponse> addAddress(
            @RequestParam(value = "identificationId", required = false, defaultValue = "0")
            String identificationId,
            @RequestBody AddressRequestList request
    );

    @PutMapping("{id}")
    AddressResponse updateAddress(
            @PathVariable String id,
            @RequestBody AddressRequest request
    );

    @DeleteMapping("{id}")
    void deleteAddress(@PathVariable String id);
}
