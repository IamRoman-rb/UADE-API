package com.uade.tpo.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    public Compra(float valor, Usuario usuario, List<Item> items){
        this.valor = valor;
        this.usuario = usuario;
        this.items = items;
        this.fechaHora = LocalDateTime.now();
    }

    public Compra() {}
}
