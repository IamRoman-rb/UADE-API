package com.uade.tpo.marketplace.service;


import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.ValorAtributoProducto;
import com.uade.tpo.marketplace.enums.Estados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface ProductoService {

    public Producto crearProducto(String nombre, Date fecha, LocalDateTime hora, float valor, String descripcion, String foto, int cantida, int descuento, Usuario usuario, Categoria categoria, ArrayList<ValorAtributoProducto> datos);

    public String getId();

    public String getNombre();

    public Date getFecha();

    public LocalDateTime getHora();

    public float getValor();

    public String getDescripcion();

    public String getFoto();

    public int getCantidad();

    public int getDescuento();

    public Usuario getUsuario();

    public Categoria getCategoria();

    public Estados getEstado();

    public ArrayList<ValorAtributoProducto> getDatos();

    public ArrayList<Producto> getProductos();

    public Optional<Producto> findByNombre(String nombre);

    public Optional<Producto> findByCategoria(Categoria categoria);

    public void setNombre(String nombre);

    public void setFecha(Date fecha);

    public void setHora(LocalDateTime hora);

    public void setValor(float valor);

    public void setDescripcion(String descripcion);

    public void setFoto(String foto);

    public void setCantidad(int cantidad);

    public void setUsuario(Usuario usuario);

    public void setCategoria(Categoria categoria);

    public void setEstado(Estados estado);

}
