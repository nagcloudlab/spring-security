package com.example.demo;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

public class EtsAccessDecisionManager implements AccessDecisionManager {

    private final List<AccessDecisionVoter<?>> voters;

    public EtsAccessDecisionManager(List<AccessDecisionVoter<?>> voters) {
        this.voters = voters;
    }

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException {
        for (AccessDecisionVoter voter : voters) {
            int result = voter.vote(authentication, object, configAttributes);
            if (result == AccessDecisionVoter.ACCESS_GRANTED) {
                return; // allow
            }
        }
        throw new AccessDeniedException("Access denied by EtsAccessDecisionManager");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
