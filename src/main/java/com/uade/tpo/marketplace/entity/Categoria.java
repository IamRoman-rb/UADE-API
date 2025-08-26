package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.Estados;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    // Relación recursiva: una categoría puede tener una categoría padre
    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

    // Relación inversa: una categoría puede tener varias subcategorías
    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> subcategorias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estados estado = Estados.ACTIVO;

    public Categoria(String nombre, String descripcion, Categoria categoriaPadre){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaPadre = categoriaPadre;
        this.estado = Estados.ACTIVO;
    }

    public Categoria(){}
}
