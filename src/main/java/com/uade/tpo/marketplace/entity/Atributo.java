package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nombre;

    public Atributo(String nombre){
        this.nombre = nombre;
    }

    public Atributo(){}

}
