package com.example.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here you would typically fetch the user from a database or another source
        // For demonstration purposes, we will return a dummy user
        if ("user".equals(username)) {
            return org.springframework.security.core.userdetails.User.withUsername("user")
                    .password("{noop}password") // {noop} indicates no password encoding
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

}
