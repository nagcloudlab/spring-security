package com.example.demo;


import java.util.List;

public class EtsUser {
    private final String username;
    private final String password;
    private final String domain;
    private final List<String> roles;

    public EtsUser(String username, String password, String domain, List<String> roles) {
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.roles = roles;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDomain() { return domain; }
    public List<String> getRoles() { return roles; }
}
