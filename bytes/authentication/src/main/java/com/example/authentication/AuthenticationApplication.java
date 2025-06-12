package com.example.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthenticationApplication {

	@GetMapping("/public")
	public String publicResource() {
		return "This is a public resource accessible to everyone.";
	}

	@GetMapping("/user")
	public String privateResource() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "This is a private resource accessible to authenticated users. User: " + authentication.getName();
	}

	@GetMapping("/admin")
	public String adminResource() {
		return "This is an admin resource accessible only to users with the ADMIN role.";
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
