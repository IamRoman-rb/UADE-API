package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.enums.Estados;
import com.uade.tpo.marketplace.enums.Role;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UsuarioRequest {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int dni;
    private Role tipo;
    private ArrayList<Compra> compras;
    private Estados estado;
}
