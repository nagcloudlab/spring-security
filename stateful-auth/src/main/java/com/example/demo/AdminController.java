package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";  // Show admin.html only for ROLE_ADMIN
    }
}
