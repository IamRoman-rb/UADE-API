package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Item;
import com.uade.tpo.marketplace.entity.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface CompraService {
    public Compra crearCompra(float valor, Usuario usuario, ArrayList<Item> items, Date fecha, LocalDateTime hora);

    
}
