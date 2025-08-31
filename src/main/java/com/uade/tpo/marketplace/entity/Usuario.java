package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import com.uade.tpo.marketplace.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // En producción: encriptar con BCrypt

    @Column(nullable = false, unique = true)
    private int dni;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role tipo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estados estado;

    public Usuario(String nombre, String apellido, String email, String password, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.tipo = Role.COMPRADOR;
        this.estado = Estados.ACTIVO;
    }

    public Usuario() {}
}
