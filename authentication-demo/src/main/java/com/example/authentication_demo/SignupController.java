package com.example.authentication_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam String domain) {

        boolean registered = userStore.register(username, password, domain);

        if (!registered) {
            return "redirect:/register?error";
        }
        return "redirect:/login?success";
    }
}
