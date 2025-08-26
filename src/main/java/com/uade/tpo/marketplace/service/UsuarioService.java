package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.enums.Role;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicadoException;

import java.util.ArrayList;
import java.util.Optional;

public interface UsuarioService {

    public Usuario crearUsuario(String nombre, String apellido, String maill, String password, int dni, Role tipo) throws UsuarioDuplicadoException;

    public String getId();

    public String getNombre();

    public String getApellido();

    public String getMail();

    public int getDni();

    public Role getTipoUsuario();

    public ArrayList<Compra> getCompras();

    public ArrayList<Usuario> getByNombre(String nombre);

    public ArrayList<Usuario> getByEmail(String email);

    public Optional<Usuario> findById();

    public ArrayList<Usuario> getUsuarios();

    public void setNombre(String nombre);

    public void setApellido(String apellido);

    public void setMail(String mail);

    public void setPassword(String password);

    public void setDni(int dni);

    public void setTipo(Role tipo);


}
