package com.uade.tpo.marketplace.configs;

import com.uade.tpo.marketplace.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        // AUTH - Público
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // USUARIOS
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyAuthority(Role.ADMINISTRADOR.name(), Role.COMPRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasAnyAuthority(Role.ADMINISTRADOR.name(), Role.COMPRADOR.name())

                        // PRODUCTOS - Público (solo lectura)
                        .requestMatchers(HttpMethod.GET, "/productos/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/productos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/productos/categoria/**").permitAll()

                        // PRODUCTOS - Creación y edición (solo admin)
                        .requestMatchers(HttpMethod.POST, "/productos/crear").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/productos/editar/{id}").hasAuthority(Role.ADMINISTRADOR.name())

                        // PRODUCTOS - Cambio de estado (tu estructura: accion/{id})
                        .requestMatchers(HttpMethod.POST, "/productos/desactivar/{id}").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/productos/activar/{id}").hasAuthority(Role.ADMINISTRADOR.name())

                        // PRODUCTOS - Admin puede ver todos (activos e inactivos)
                        .requestMatchers(HttpMethod.GET, "/productos/todos").hasAuthority(Role.ADMINISTRADOR.name())

                        // CARRITO - Solo compradores
                        .requestMatchers("/carrito/**").hasAuthority(Role.COMPRADOR.name())

                        // COMPRAS - Solo compradores (sus propias compras)
                        .requestMatchers(HttpMethod.GET, "/compras/").hasAuthority(Role.COMPRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/compras/{id}").hasAuthority(Role.COMPRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/compras/checkout").hasAuthority(Role.COMPRADOR.name())

                        // COMPRAS - Admin (ver todas las compras)
                        .requestMatchers(HttpMethod.GET, "/compras/todas").hasAuthority(Role.ADMINISTRADOR.name())

                        // CATEGORIAS - Público (solo lectura)
                        .requestMatchers(HttpMethod.GET, "/categorias/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categorias/{id}").permitAll()

                        // CATEGORIAS - Solo Admin (gestión) - Misma estructura
                        .requestMatchers(HttpMethod.POST, "/categorias/crear").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/categorias/editar/{id}").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/categorias/desactivar/{id}").hasAuthority(Role.ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/categorias/activar/{id}").hasAuthority(Role.ADMINISTRADOR.name())

                        // ATRIBUTOS - Solo Admin
                        .requestMatchers("/atributos/**").hasAuthority(Role.ADMINISTRADOR.name())

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}