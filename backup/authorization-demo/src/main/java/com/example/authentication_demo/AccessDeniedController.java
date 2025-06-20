package com.example.authentication_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("/access-denied")
    public String denied() {
        return "access-denied";
    }
}
