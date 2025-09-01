package com.uade.tpo.marketplace.controllers.productos;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.ValorAtributoProducto;
import com.uade.tpo.marketplace.enums.Estados;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
public class ProductoRequest {
    private String id;
    private String nombre;
    private Date fecha;
    private LocalDateTime hora;
    private float valor;
    private String descripcion;
    private String foto;
    private int cantidad;
    private int descuento;
    private Categoria categoria;
    private Estados estado;
    private ArrayList<ValorAtributoProducto> datos;
}
