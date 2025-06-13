package com.example.demo;

/*
    - Employee-1 -> EMPLOYEE
    - Employee-2 -> EMPLOYEE
 */

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class EtsOwnershipVoter implements AccessDecisionVoter<RequestAuthorizationContext> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true; // apply to all secured methods or URLs
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, RequestAuthorizationContext context, Collection<ConfigAttribute> attributes) {

        System.out.println("Voting on ownership for: " + context.getClass().getName());

        String currentUser = authentication.getName();
        // Example logic: allow only if user == 'admin' or accessing their own resource
        HttpServletRequest request = context.getRequest();
        String path = request.getRequestURI(); // e.g., /profile/alice
        if (path.contains(currentUser) || currentUser.equals("admin")) {
            return ACCESS_GRANTED;
        }
        return ACCESS_ABSTAIN; // don't vote if we don't want to interfere
    }
}
