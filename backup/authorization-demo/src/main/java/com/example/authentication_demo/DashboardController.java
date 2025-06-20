package com.example.authentication_demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("authorities", authentication.getAuthorities());

        if (authentication instanceof EtsAuthenticationToken token) {
            model.addAttribute("domain", token.getDomain());
        } else {
            model.addAttribute("domain", "unknown");
        }

        return "dashboard";
    }
}
