package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Spring Security Config Class
 */
@Configuration
// Register DelegatingFilterProxy to use Spring Security
// FilterChainProxy used to delegate to the appropriate filter chain
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * User details service in memory user details manager.
	 *
	 * @return the in memory user details manager
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user1@example.com")
				.password("user1")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * Filter chain security filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/**").hasRole("USER")
						.anyRequest().authenticated())
				.formLogin(withDefaults())
				.csrf(AbstractHttpConfigurer::disable); // Disable CSRF for simplicity in this example
		// For H2 Console
		http.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
		return http.build();
	}

}
