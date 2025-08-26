package com.uade.tpo.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "valores_atributo_producto",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"producto_id", "atributo_id"})
        }
)
public class ValorAtributoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "atributo_id", nullable = false)
    private Atributo atributo;

    public ValorAtributoProducto(String valor, Producto producto, Atributo atributo){
        this.valor = valor;
        this.producto = producto;
        this.atributo = atributo;
    }

    public ValorAtributoProducto(){}
}
