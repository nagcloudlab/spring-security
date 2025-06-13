package com.example.authentication_demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserStore {
    private final Map<String, EtsUser> users = new HashMap<>();

    public InMemoryUserStore() {
        // Pre-populate with some users
        register("user1", "password1", "domain1");
        register("user2", "password2", "domain2");
    }

    public boolean register(String username, String password, String domain) {
        if (users.containsKey(username)) return false;
        users.put(username, new EtsUser(username, password, domain));
        return true;
    }

    public Optional<EtsUser> find(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
