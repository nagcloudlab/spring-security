package com.example.authentication_demo;

public class EtsUser {
    private final String username;
    private final String password;
    private final String domain;

    public EtsUser(String username, String password, String domain) {
        this.username = username;
        this.password = password;
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomain() {
        return domain;
    }
}
