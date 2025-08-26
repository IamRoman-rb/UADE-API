package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column
    private String foto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int descuento;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estados estado = Estados.ACTIVO;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValorAtributoProducto> datos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    public Producto(String nombre, float valor, String descripcion, String foto, int cantidad, int descuento, Categoria categoria, List<ValorAtributoProducto> datos){
        this.fechaHora = LocalDateTime.now();
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
