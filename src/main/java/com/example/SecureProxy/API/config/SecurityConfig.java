package com.example.SecureProxy.API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter filter;

    public SecurityConfig(JwtAuthFilter filter){
        this.filter=filter;
    }


    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {

        http.csrf(cs->cs.disable());

        http.sessionManagement(s ->
                s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()   // ⭐ NEW (exact matcher)
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/xyz/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );

        http.addFilterBefore(filter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
