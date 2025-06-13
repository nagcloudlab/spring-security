package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class EtsSecurityConfiguration {

    private EtsAuthenticationProvider authenticationProvider;

    @Autowired
    public EtsSecurityConfiguration(EtsAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

//    @Bean
//    public AuthenticationManagerBuilder authenticationManagerBuilder(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//            http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//        return authenticationManagerBuilder;
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return new ProviderManager(List.of(authenticationProvider));
    }

    @Bean
    public EtsAuthenticationFilter etsAuthenticationFilter(AuthenticationManager authenticationManager) {
        EtsAuthenticationFilter etsAuthenticationFilter = new EtsAuthenticationFilter(authenticationManager);
        etsAuthenticationFilter.setFilterProcessesUrl("/authenticate");
        return etsAuthenticationFilter;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(List<AccessDecisionVoter<?>> voters) {
        return new EtsAccessDecisionManager(voters);
    }

    @Bean
    public AuthorizationManager<RequestAuthorizationContext> customAuthorizationManager(AccessDecisionManager adm) {
        return (authenticationSupplier, context) -> {
            Authentication authentication = authenticationSupplier.get();
            try {
                adm.decide(authentication, context, List.of(new SecurityConfig("ROLE_USER")));  // Or any default attr
                return new AuthorizationDecision(true);
            } catch (AccessDeniedException e) {
                return new AuthorizationDecision(false);
            }
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationManager<RequestAuthorizationContext> customAuthz) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/login", "/register", "/favicon.ico", "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll() // 🔐 allow form POST
                        .requestMatchers("/dashboard").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/profile/**").access(customAuthz)
                        .anyRequest().authenticated()
                )

                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .addFilterBefore(etsAuthenticationFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}
