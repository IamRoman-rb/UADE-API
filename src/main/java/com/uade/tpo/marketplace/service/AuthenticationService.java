package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.configs.JwtService;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationRequest;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationResponse;
import com.uade.tpo.marketplace.controllers.auth.RegistroRequest;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        System.out.println("Intentando autenticar: " + request.getEmail());

        // Verificar primero si el usuario existe y está activo
        var userOptional = repository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            System.out.println("Usuario no encontrado: " + request.getEmail());
            throw new BadCredentialsException("Usuario o contraseña incorrectos");
        }

        var user = userOptional.get();
        System.out.println("Usuario encontrado: " + user.getEmail() + ", Estado: " + user.getEstado());

        if (!user.isEnabled()) {
            System.out.println("Usuario inactivo: " + request.getEmail());
            throw new RuntimeException("Usuario inactivo");
        }

        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            System.out.println("Autenticación exitosa: " + auth.isAuthenticated());

        } catch (BadCredentialsException e) {
            System.out.println("Credenciales incorrectas para: " + request.getEmail());
            throw new BadCredentialsException("Usuario o contraseña incorrectos");
        } catch (Exception e) {
            System.out.println("Error en autenticación: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error en la autenticación");
        }
        user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
