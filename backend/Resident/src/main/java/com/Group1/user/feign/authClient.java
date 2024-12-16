package com.Group1.user.feign;

import com.Group1.user.dto.authentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authentication", url = "http://localhost:9996/auth")
public interface authClient {
    @GetMapping("/getuser/{userid}")
    public authentication getUser(@PathVariable String userid);

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable String userid);
}
