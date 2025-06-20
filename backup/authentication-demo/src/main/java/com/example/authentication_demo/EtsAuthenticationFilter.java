package com.example.authentication_demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;

public class EtsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public EtsAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String domain = request.getParameter("domain");

        EtsAuthenticationToken authRequest = new EtsAuthenticationToken(username, password, domain);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // Set the authenticated user into the security context
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // Create session and attach auth (important!)
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        // Redirect to protected page
        response.sendRedirect("/dashboard");
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.sendRedirect("/login?error=true");
    }
}
