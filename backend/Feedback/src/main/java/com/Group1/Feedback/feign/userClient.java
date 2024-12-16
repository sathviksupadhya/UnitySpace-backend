package com.Group1.Feedback.feign;

import com.Group1.Feedback.dto.userFullDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "http://localhost:9991/api/residents")
public interface userClient {
    @GetMapping("/{userId}")
    public userFullDetails getResidentById(@PathVariable String userId);
}
