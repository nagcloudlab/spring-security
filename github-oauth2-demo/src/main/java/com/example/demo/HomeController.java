package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, OAuth2AuthenticationToken token) {
        if (token != null) {
            model.addAttribute("userName", token.getPrincipal().getAttribute("login"));
            model.addAttribute("avatar", token.getPrincipal().getAttribute("avatar_url"));
        }
        return "home";
    }
}
