package com.Group1.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="alert", url="http://localhost:9994/api/alert")
public interface alertClient {
    @PostMapping("/createAlertforUsers/{userId}")
    public ResponseEntity<String> createAlertByUserId(@PathVariable String userId);
}
