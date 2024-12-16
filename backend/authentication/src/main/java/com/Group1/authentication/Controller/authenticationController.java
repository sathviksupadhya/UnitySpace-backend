package com.Group1.authentication.Controller;

import com.Group1.authentication.Model.authentication;
import com.Group1.authentication.Service.authenticationService;
import com.Group1.authentication.Service.jwtService;
import com.Group1.authentication.dto.authdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class authenticationController {

    @Autowired
    private authenticationService authenticationservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtService jwrs;

    @PostMapping("/register")
    public ResponseEntity<authentication> register(@RequestBody authdto auth) {
        return ResponseEntity.ok(authenticationservice.register(auth));
    }

    @PostMapping("/validate/user")
    public Map<String, String> validateUser(@RequestBody authdto auth) {
        System.out.println("user : " + auth);
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        Map<String, String> response = new HashMap<>();
        if (authenticate.isAuthenticated()) {
            response.put("token", jwrs.generateToken(auth.getUserName()));  // Store token
            response.put("userId", authenticationservice.getuserId(auth.getUserName()));
            return response;
        }
        return null;
    }

    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam("token") String token) {
        jwrs.validateToken(token);
        return true;
    }

    @GetMapping("/getuser/{userid}")
    public authentication getUser(@PathVariable String userid) {
        return authenticationservice.getUser(userid);
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable String userid) {
        return ResponseEntity.ok(authenticationservice.deleteUser(userid));
    }


}
