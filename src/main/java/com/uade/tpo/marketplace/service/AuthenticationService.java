package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.configs.JwtService;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationRequest;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationResponse;
import com.uade.tpo.marketplace.controllers.auth.RegistroRequest;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistroRequest request) {
        var user = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
