package org.example;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome( OAuth2AuthenticationToken authentication) {
        var attributes = authentication.getPrincipal().getAttributes();
        System.out.println(attributes.get("login"));
        System.out.println(attributes.get("name"));
        System.out.println(attributes.get("avatar_url"));
        authentication.getAuthorities().forEach(System.out::println);
        return "Welcome to Welcome Controller";
    }

}
