package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Categoria categoria;

    @Column
    private Estados estado;

    public Categoria(String nombre, String descripcion, Categoria categoria){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = Estados.ACTIVO;
    }

    public Categoria(){}

}
