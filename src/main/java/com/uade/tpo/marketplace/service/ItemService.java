package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Item;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Usuario;

import java.time.LocalDateTime;
import java.util.Date;

public interface ItemService {
    public Item crearItem(Producto producto, Usuario usuario, int cantidad, float valor, Date fecha, LocalDateTime hora);

    public String getId();

    public Producto getProducto();

    public Usuario getUsuario();

    public int getCantidad();

    public float getValor();

    public Date getFecha();

    public LocalDateTime getHora();

    public void setProducto(Producto producto);

    public void setUsuario(Usuario usuario);

    public void setCantidad(int cantidad);

    // El item es el que se encuentra en el carrito, por ende, no tendria sentido setearle la fecha, la hora, o el valor
}
