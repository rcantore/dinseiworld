package com.michead.dinseiworld.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    DataSource dataSource;

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    /**
     * Require login to access internal pages and configure login form.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Not using Spring CSRF here to be able to use plain HTML for the login page
        http.csrf(csrf -> csrf.disable())

                // Register our CustomRequestCache, that saves unauthorized access attempts, so
                // the user is redirected after login.
                .requestCache(cache -> cache.requestCache(new CustomRequestCache()))

                // Restrict access to our application.
                .authorizeHttpRequests(authz -> authz
                    // Allow static resources
                    .requestMatchers("/VAADIN/**", "/favicon.ico", "/robots.txt", 
                        "/manifest.webmanifest", "/sw.js", "/offline.html",
                        "/icons/**", "/images/**", "/styles/**", "/h2-console/**").permitAll()
                    // Allow all Vaadin internal requests.
                    .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                    // Allow all requests by logged in users.
                    .anyRequest().authenticated()
                )
                // Configure the login page.
                .formLogin(form -> form
                    .loginPage(LOGIN_URL).permitAll()
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
                    .failureUrl(LOGIN_FAILURE_URL)
                )
                // Configure logout
                .logout(logout -> logout.logoutSuccessUrl(LOGOUT_SUCCESS_URL));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("user")
                .password("$2a$10$xjA04l5/vOCbj6hX3Adaf.eiTs2zGbGHiEj/8oq.x.csgV0iuCHzC")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("admin")
                .password("$2a$10$3Qrb9ORsKmnm78dReTsQtOuvXlNlzbghPDp5Ef0qUuvCJP4jSJoYS")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
//        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
