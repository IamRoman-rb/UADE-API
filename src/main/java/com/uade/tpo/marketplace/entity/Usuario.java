package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import com.uade.tpo.marketplace.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private int dni;

    @Column
    private Role tipo;

    @Column
    private ArrayList<Compra> compraa;

    @Column
    private Estados estado;

    public Usuario(String nombre, String apellido, String email, String password, int dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password; // Encriptar, ver la proxima clase :)
        this.dni = dni;
        this.tipo = Role.COMPRADOR;
        this.estado = Estados.ACTIVO;
    }

    public Usuario(){}
}
