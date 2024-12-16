package com.Group1.authentication.Service;


import com.Group1.authentication.Model.authentication;
import com.Group1.authentication.Model.authUserDetails;
import com.Group1.authentication.Repository.authenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    authenticationRepo userCredentialsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<authentication> user = userCredentialsDao.findByUserName(username);
        System.out.println("user 2: " + user);
        return user.map(authUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username/password not valid!"));
    }
}
