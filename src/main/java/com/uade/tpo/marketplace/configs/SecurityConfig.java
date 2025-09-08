package com.uade.tpo.marketplace.configs;

import com.uade.tpo.marketplace.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/usuarios").hasRole("ADMINISTRADOR")
                        .requestMatchers("/usuarios/{id}").hasAnyAuthority("ADMINISTRADOR", "COMPRADOR")
                        .requestMatchers("/productos/").permitAll()
                        .requestMatchers("/productos/{id}").permitAll()
                        .requestMatchers("/productos/categoria/").permitAll()
                        .requestMatchers("/productos/categoria/**").permitAll()
                        .requestMatchers("/productos/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers("/productos/editar/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers("/compras/").permitAll()
                        .requestMatchers("/compras/{id}").permitAll()
                        .requestMatchers("/compras/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers("/categorias/").permitAll()
                        .requestMatchers("/categorias/{id}").permitAll()
                        .requestMatchers("/categorias/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers("/atributos/**").hasAuthority("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}