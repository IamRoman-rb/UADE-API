package com.uade.tpo.marketplace.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false) // relación con Compra
    private Compra compra;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    public Item(Producto producto, Usuario usuario, Compra compra, int cantidad, float valor){
        this.producto = producto;
        this.usuario = usuario;
        this.compra = compra;
        this.cantidad = cantidad;
        this.valor = valor;
        this.fechaHora = LocalDateTime.now();
    }

    public Item() {}
}
