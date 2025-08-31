package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicadoException;
import com.uade.tpo.marketplace.exceptions.UsuarioNotFoundException;
import com.uade.tpo.marketplace.repository.UsuarioRepository;
import com.uade.tpo.marketplace.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <--- Esta es la importación CLAVE
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImp(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioDuplicadoException {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new UsuarioDuplicadoException("El email ya está registrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(String id, Usuario usuario) throws UsuarioDuplicadoException, UsuarioNotFoundException {
        return usuarioRepository.findById(id).map(existing -> {
            if (!existing.getEmail().equals(usuario.getEmail()) &&
                    usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                throw new UsuarioDuplicadoException("El email ya está registrado");
            }

            existing.setNombre(usuario.getNombre());
            existing.setApellido(usuario.getApellido());
            existing.setEmail(usuario.getEmail());

            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            existing.setDni(usuario.getDni());
            existing.setTipo(usuario.getTipo());
            existing.setEstado(usuario.getEstado());
            return usuarioRepository.save(existing);
        }).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Asegúrate de que este método exista en tu UsuarioRepository
    @Override
    public List<Usuario> findByNombre(String nombre) {
        // Asumiendo que tu repositorio tiene un método como este
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public void deleteUsuario(String id) {
        if (!usuarioRepository.existsById(id)) { // Buena práctica para verificar si existe antes de eliminar
            throw new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}