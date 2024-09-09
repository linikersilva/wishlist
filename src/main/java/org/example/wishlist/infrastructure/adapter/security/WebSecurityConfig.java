package org.example.wishlist.infrastructure.adapter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // em um cenário de ambiente de produção real, onde essa aplicação realmente fosse um microsserviço que se comunicaria com outros microsserviços, a parte de Spring Security seria mais robusta
        // como a parte de segurança não está listada como requisito no pdf do desafio, optei por simplificá-la e acelerar meu desenvolvimento focando nos usecases da wishlist e na arquitetura
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}

