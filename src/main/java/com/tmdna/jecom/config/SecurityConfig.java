package com.tmdna.jecom.config;

import com.tmdna.jecom.security.filters.SecurityAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.addFilterBefore(authenticationFilter, AuthorizationFilter.class)

                .authorizeHttpRequests(
                        matcher -> matcher
                                .requestMatchers("/api/products/**").permitAll()
                                .requestMatchers("/api/images/upload").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }
}
