package com.example.authentication_demo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SignupController {

    private final InMemoryUserStore userStore; // or real DB-backed service

    public SignupController(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostConstruct
    public void initAdmin() {
        userStore.register("admin", "secret", "example.com", List.of("ROLE_ADMIN"));
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String domain) {

        boolean success = userStore.register(username, password, domain, List.of("ROLE_USER"));

        return success ? "redirect:/login?success" : "redirect:/register?error";
    }

}
