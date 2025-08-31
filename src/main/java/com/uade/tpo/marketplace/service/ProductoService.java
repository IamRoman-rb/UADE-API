package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto crearProducto(Producto producto);

    List<Producto> getProductos();

    Optional<Producto> findById(String id);

    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCategoria(Categoria categoria);

    Producto actualizarProducto(String id, Producto producto);

    void eliminarProducto(String id);
}
