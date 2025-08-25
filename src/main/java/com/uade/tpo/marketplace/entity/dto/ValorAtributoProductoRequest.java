package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Atributo;
import com.uade.tpo.marketplace.entity.Producto;
import lombok.Data;

@Data
public class ValorAtributoProductoRequest {
    private String id;
    private String valor;
    private Producto producto;
    private Atributo atributo;
}
