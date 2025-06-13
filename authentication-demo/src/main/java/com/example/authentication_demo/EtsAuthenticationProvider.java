package com.example.authentication_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EtsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InMemoryUserStore userStore;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EtsAuthenticationToken token = (EtsAuthenticationToken) authentication;
        String username = token.getName();
        String password = token.getCredentials().toString();
        String domain = token.getDomain();

        Optional<EtsUser> user = userStore.find(username);

        if (user.isPresent()
                && user.get().getPassword().equals(password)
                && user.get().getDomain().equals(domain)) {

            return new EtsAuthenticationToken(username, password, domain,
                    List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }

        throw new BadCredentialsException("Invalid credentials or domain");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(EtsAuthenticationToken.class);
    }
}
