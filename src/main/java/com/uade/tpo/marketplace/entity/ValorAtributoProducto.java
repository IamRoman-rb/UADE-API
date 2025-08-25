package com.uade.tpo.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ValorAtributoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String valor;

    @Column
    private Producto producto;

    @Column
    private Atributo atributo;

    public ValorAtributoProducto(String valor, Producto producto, Atributo atributo){
        this.valor = valor;
        this.producto = producto;
        this.atributo = atributo;
    }
    public ValorAtributoProducto(){}

}
