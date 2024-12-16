package com.Group1.Reminder.feign;

import com.Group1.Reminder.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user", url = "http://localhost:9991/api/residents")
public interface userClient {
    @GetMapping("/{userId}")
    public User getResidentById(@PathVariable String userId);

    @GetMapping("/allUsers")
    public List<User> getAllUsers();
}
