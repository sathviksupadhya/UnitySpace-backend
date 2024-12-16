package com.Group1.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user", url = "http://localhost:9991/api/residents")
public interface userClient {

    @PostMapping("/create/{id}")
    public String create(@PathVariable String id);
}
