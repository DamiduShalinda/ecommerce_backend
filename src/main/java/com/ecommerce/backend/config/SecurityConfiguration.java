package com.ecommerce.backend.config;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ecommerce.backend.model.Permission.*;
import static com.ecommerce.backend.model.Role.ADMIN;
import static com.ecommerce.backend.model.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        JsonSubTypes.Type MANAGER_PUT;
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/**")
                .permitAll()
                .requestMatchers("/api/management/**").hasAnyRole(ADMIN.name() , MANAGER.name())
                .requestMatchers("/api/admin/**").hasRole(ADMIN.name())
                .requestMatchers(GET , "/api/management/**").hasAnyAuthority(ADMIN_READ.name() , MANAGER_READ.name())
                .requestMatchers(POST , "/api/management/**").hasAnyAuthority(ADMIN_CREATE.name() , MANAGER_CREATE.name())
                .requestMatchers(PUT , "/api/management/**").hasAnyAuthority(ADMIN_UPDATE.name() , MANAGER_UPDATE.name())
                .requestMatchers(DELETE , "/api/management/**").hasAnyAuthority(ADMIN_DELETE.name() , MANAGER_DELETE.name())

//                .requestMatchers(GET , "/api/admin/**").hasAuthority(ADMIN_READ.name())
//                .requestMatchers(POST , "/api/admin/**").hasAuthority(ADMIN_CREATE.name())
//                .requestMatchers(PUT , "/api/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                .requestMatchers(DELETE , "/api/admin/**").hasAuthority(ADMIN_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
}
