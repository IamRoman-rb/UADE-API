package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private float valor;

    @Column
    private Usuario usuario;

    @Column
    private ArrayList<Item> items;

    @Column
    private Date fecha;

    @Column
    private LocalDateTime hora;

    public Compra(float valor, Usuario usuario, ArrayList<Item> items){
        this.valor = valor;
        this.usuario = usuario;
        this.items = items;
        this.fecha = new Date();
        this.hora = LocalDateTime.now();
    }

    public Compra(){}
}
