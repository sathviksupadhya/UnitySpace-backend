package com.Group1.authentication.Service;

import com.Group1.authentication.Model.authentication;
import com.Group1.authentication.Repository.authenticationRepo;
import com.Group1.authentication.dto.authdto;
import com.Group1.authentication.feign.alertClient;
import com.Group1.authentication.feign.userClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class authenticationService {

    @Autowired
    private authenticationRepo authrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private userClient userclient;
    @Autowired
    private alertClient alertclient;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeAdminUser() {
        if (authrepo.findByUserName("Admin").isEmpty()) {
            authentication a = new authentication();
            a.setUserName("Admin");
            a.setPassword(passwordEncoder.encode("Admin"));
            a.setRole("ADMIN");
            authentication b = authrepo.save(a);
            userclient.create(b.getUserId());
        }
    }

    public authentication register(authdto auth) {
        authrepo.findByUserName(auth.getUserName()).ifPresent(u -> {
            throw new RuntimeException("Username already exists");
        });
        authentication a = new authentication();
        a.setUserName(auth.getUserName());
        a.setPassword(passwordEncoder.encode(auth.getPassword()));
        authentication b = authrepo.save(a);
        userclient.create(b.getUserId());
        alertclient.createAlertByUserId(b.getUserId());
        return b;
    }

    public String getuserId(String userName) {
        Optional<authentication> a = authrepo.findByUserName(userName);
        if(a.isPresent()) {
            authentication b = a.get();
            return b.getUserId();
        }
        return null;
    }

    public authentication getUser(String userid) {
        return authrepo.findByUserId(userid);
    }

    public String deleteUser(String userid) {
        authrepo.deleteById(userid);
        return "User deleted";
    }
}
