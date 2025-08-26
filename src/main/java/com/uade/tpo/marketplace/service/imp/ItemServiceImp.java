package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Item;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.service.ItemService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ItemServiceImp implements ItemService {
    @Override
    public Item crearItem(Producto producto, Usuario usuario, int cantidad, float valor, Date fecha, LocalDateTime hora) {
        return null;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public Producto getProducto() {
        return null;
    }

    @Override
    public Usuario getUsuario() {
        return null;
    }

    @Override
    public int getCantidad() {
        return 0;
    }

    @Override
    public float getValor() {
        return 0;
    }

    @Override
    public Date getFecha() {
        return null;
    }

    @Override
    public LocalDateTime getHora() {
        return null;
    }

    @Override
    public void setProducto(Producto producto) {

    }

    @Override
    public void setUsuario(Usuario usuario) {

    }

    @Override
    public void setCantidad(int cantidad) {

    }
}
