package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.enums.Role;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicadoException;
import com.uade.tpo.marketplace.repository.UsuarioRepository;
import com.uade.tpo.marketplace.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario crearUsuario(String nombre, String apellido, String maill, String password, int dni, Role tipo) throws UsuarioDuplicadoException {
        return null;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getNombre() {
        return "";
    }

    @Override
    public String getApellido() {
        return "";
    }

    @Override
    public String getMail() {
        return "";
    }

    @Override
    public int getDni() {
        return 0;
    }

    @Override
    public Role getTipoUsuario() {
        return null;
    }

    @Override
    public ArrayList<Compra> getCompras() {
        return null;
    }

    @Override
    public ArrayList<Usuario> getByNombre(String nombre) {
        return null;
    }

    @Override
    public ArrayList<Usuario> getByEmail(String email) {
        return null;
    }

    @Override
    public Optional<Usuario> findById() {
        return Optional.empty();
    }

    @Override
    public ArrayList<Usuario> getUsuarios() {
        return null;
    }

    @Override
    public void setNombre(String nombre) {

    }

    @Override
    public void setApellido(String apellido) {

    }

    @Override
    public void setMail(String mail) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setDni(int dni) {

    }

    @Override
    public void setTipo(Role tipo) {

    }
}
