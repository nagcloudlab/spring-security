package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EtsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InMemoryUserStore userStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EtsAuthenticationToken token = (EtsAuthenticationToken) authentication;

        EtsUser user = userStore.find(token.getName())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!passwordEncoder.matches(token.getCredentials().toString(), user.getPassword())
                || !user.getDomain().equals(token.getDomain())) {
            throw new BadCredentialsException("Invalid credentials or domain");
        }

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new EtsAuthenticationToken(user.getUsername(), user.getPassword(), user.getDomain(), authorities);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(EtsAuthenticationToken.class);
    }
}
