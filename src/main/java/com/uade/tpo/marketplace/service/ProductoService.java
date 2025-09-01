package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.controllers.productos.ProductoRequest;
import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.ValorAtributoProducto;
import com.uade.tpo.marketplace.enums.Estados;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicadoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicadoException;

    List<Producto> getProductos();

    Optional<Producto> findById(String id);

    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCategoria(Categoria categoria);

    Producto actualizarProducto(String id, Producto producto);

    void eliminarProducto(String id);

    Producto actualizarProducto(ProductoRequest request);
}
