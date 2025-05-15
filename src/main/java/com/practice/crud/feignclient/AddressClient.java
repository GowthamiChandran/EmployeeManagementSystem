package com.practice.crud.feignclient;

import com.practice.crud.dto.AddressResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="abc",url = "http://localhost:9091/api")
public interface AddressClient {

    @GetMapping("/address/{id}")
    AddressResponseDto getAddressByEmployeeId(@PathVariable("id") Long id);
}
