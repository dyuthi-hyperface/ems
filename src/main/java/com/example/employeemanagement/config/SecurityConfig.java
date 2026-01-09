package com.example.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 1️⃣ Disable CSRF (we are building REST APIs)
                .csrf(csrf -> csrf.disable())

                // 2️⃣ Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login will go here
                        .anyRequest().authenticated()               // everything else secured
                )

                // 3️⃣ Enable HTTP Basic for now (TEMPORARY)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
