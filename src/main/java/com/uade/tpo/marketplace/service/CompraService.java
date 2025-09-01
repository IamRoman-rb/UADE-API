package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.controllers.compras.CompraRequest;

import java.util.List;

public interface CompraService {
    Compra crearCompra(CompraRequest compraRequest);

    List<Compra> findAll();

    List<Compra> findAllByUsuario(Usuario usuario);

    Compra findById(String id);

    List<Compra> findByFecha(java.time.LocalDateTime fecha);

    void deleteCompra(String id);
}
