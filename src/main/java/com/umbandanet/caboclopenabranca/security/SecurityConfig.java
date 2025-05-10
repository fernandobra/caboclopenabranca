package com.umbandanet.caboclopenabranca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,
                                "/api/caboclopenabranca/pessoas/**",
                                "/api/caboclopenabranca/pessoas/login",
                                "/api/caboclopenabranca/pessoas/login/exists",
                                "/api/caboclopenabranca/pessoas/email/exists",
                                "/api/caboclopenabranca/password-recovery/request",
                                "/api/caboclopenabranca/password-recovery/reset",
                                "/api/caboclopenabranca/password-recovery/**",
                                "/api/caboclopenabranca/auth/refresh").permitAll()
                        .requestMatchers("/api/caboclopenabranca/pessoas/login/exists").permitAll()
                        .requestMatchers("/api/caboclopenabranca/pessoas/email/exists").permitAll()
                        .requestMatchers("/api/caboclopenabranca/auth/refresh").permitAll()
                        .requestMatchers("/api/caboclopenabranca/pessoas/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/datalimpeza/calendario").authenticated()
                        .requestMatchers("/api/caboclopenabranca/datalimpeza/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/grupoLimpeza/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/grupopessoas/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/orixadiasemana/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/sessao/**").authenticated()
                        .requestMatchers("/api/caboclopenabranca/materialpessoa/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}