package com.uade.tpo.marketplace.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "atributos")
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String nombre;

    public Atributo(String nombre){
        this.nombre = nombre;
    }

    public Atributo(){}
}
