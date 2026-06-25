package org.example.lic_policy_managament_system.config;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/users",
                                "/api/users/login"
                        ).permitAll()

                        // Admin APIs
                        .requestMatchers(
                                "/admin/**"
                        ).hasRole("ADMIN")

                        // User Management - Admin only
                        .requestMatchers(
                                "/api/users/**"
                        ).hasRole("ADMIN")

                        // Customer + Admin
                        .requestMatchers(
                                "/api/customers/**"
                        ).hasAnyRole("ADMIN", "CUSTOMER")

                        // Customer + Admin
                        .requestMatchers(
                                "/api/policies/**"
                        ).hasAnyRole("ADMIN", "CUSTOMER")

                        // Customer + Admin
                        .requestMatchers(
                                "/api/claims/**"
                        ).hasAnyRole("ADMIN", "CUSTOMER")

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}