package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserStore {
    private final Map<String, EtsUser> users = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(String username, String rawPassword, String domain, List<String> roles) {
        if (users.containsKey(username)) return false;
        String hashed = passwordEncoder.encode(rawPassword);
        users.put(username, new EtsUser(username, hashed, domain, roles));
        return true;
    }

    public Optional<EtsUser> find(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
