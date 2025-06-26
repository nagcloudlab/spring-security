package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class RepoController {

        @Autowired
        private OAuth2AuthorizedClientService authorizedClientService;

        @GetMapping("/repos")
        @ResponseBody
        public String listRepos(OAuth2AuthenticationToken authentication) {

                OAuth2AuthorizedClient client = authorizedClientService
                                .loadAuthorizedClient(
                                                authentication.getAuthorizedClientRegistrationId(),
                                                authentication.getName());

                String accessToken = client.getAccessToken().getTokenValue();

                WebClient webClient = WebClient.builder()
                                .baseUrl("https://api.github.com")
                                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                                .build();

                return webClient.get()
                                .uri("/user/repos")
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();
        }
}
