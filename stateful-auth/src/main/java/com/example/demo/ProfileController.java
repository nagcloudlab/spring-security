package com.example.demo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/{id}")
    public String viewProfile(@PathVariable String id, Model model, Authentication authentication) {
        model.addAttribute("username", id);
        model.addAttribute("currentUser", authentication.getName());
        return "profile";
    }

    @PreAuthorize("#id == authentication.name")
    @PostMapping("/{id}/edit")
    public String editProfile(@PathVariable String id, @RequestParam String newInfo) {
        // simulate updating profile for current user
        System.out.println("Profile updated for user: " + id + " → new info: " + newInfo);
        return "redirect:/profile/" + id + "?success";
    }
}