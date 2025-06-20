package com.example.authentication_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "index";  // public home page (index.html)
    }

    @GetMapping(path = { "favicon.ico", "favicon-16x16.jpg" })
    @ResponseBody
    void returnNoFavicon() {}
}
