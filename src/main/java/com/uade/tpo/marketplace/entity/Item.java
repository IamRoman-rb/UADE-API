package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private Producto producto;

    @Column
    private Usuario usuario;

    @Column
    private int cantidad;

    @Column
    private float valor;

    @Column
    private Date fecha;

    @Column
    private LocalDateTime hora;

    public Item(Producto producto, Usuario usuario, int cantidad, float valor){
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.valor = valor;
        this.fecha = new Date();
        this.hora = LocalDateTime.now();
    }

    public Item(){}
}
