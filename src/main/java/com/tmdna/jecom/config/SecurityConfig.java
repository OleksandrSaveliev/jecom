package com.tmdna.jecom.config;

import com.tmdna.jecom.security.exception.handler.ApplicationAccessDeniedHandler;
import com.tmdna.jecom.security.exception.handler.ApplicationAuthenticationEntryPoint;
import com.tmdna.jecom.security.filters.SecurityAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    SecurityAuthenticationFilter authenticationFilter;

    public SecurityConfig(SecurityAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.addFilterBefore(authenticationFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(matcher -> matcher
                        .requestMatchers("/api/products/**").permitAll()
                        .requestMatchers("/api/images/upload").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(new ApplicationAccessDeniedHandler())
                        .authenticationEntryPoint(new ApplicationAuthenticationEntryPoint()))
                .build();
    }

    // register NoOp AuthenticationManager to avoid log printed by default autoconfiguration
    @Bean
    public AuthenticationManager noOpAuthenticationManager() {
        return authentication -> null;
    }
}
