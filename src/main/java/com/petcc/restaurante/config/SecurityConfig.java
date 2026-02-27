package com.petcc.restaurante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // Regra de Negócio: Qualquer um pode ver a documentação da API
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html").permitAll()
                
                // Regra de Negócio: Qualquer um pode ver os foods
                .requestMatchers(HttpMethod.GET, "/foods/**").permitAll()
                
                // Regra de Negócio: Qualquer usuário logado pode alterar o banco
                .requestMatchers(HttpMethod.POST, "/foods/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/foods/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/foods/**").authenticated()
                
                // O resto exige login
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}