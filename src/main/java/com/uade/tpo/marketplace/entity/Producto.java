package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nombre;

    @Column
    private Date fecha;

    @Column
    private LocalDateTime hora;

    @Column
    private float valor;

    @Column
    private String descripcion;

    @Column
    private String foto;

    @Column
    private int cantidad;

    @Column
    private int descuento;

    @Column
    private Categoria categoria;

    @Column
    private Estados estado = Estados.ACTIVO;

    @Column
    private ArrayList<ValorAtributoProducto> datos;

    public Producto(String nombre, float valor, String descripcion, String foto, int cantidad, int descuento, Categoria categoria, ArrayList<ValorAtributoProducto> datos){
        this.fecha = new Date();
        this.hora = LocalDateTime.now();
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
        this.foto = foto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.categoria = categoria;
        this.datos = datos;
    }

    public Producto(){}

}
