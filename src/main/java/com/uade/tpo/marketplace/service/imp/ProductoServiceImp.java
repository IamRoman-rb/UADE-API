package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.ValorAtributoProducto;
import com.uade.tpo.marketplace.enums.Estados;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Override
    public Producto crearProducto(String nombre, Date fecha, LocalDateTime hora, float valor, String descripcion, String foto, int cantida, int descuento, Usuario usuario, Categoria categoria, ArrayList<ValorAtributoProducto> datos) {
        return null;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getNombre() {
        return "";
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
    public float getValor() {
        return 0;
    }

    @Override
    public String getDescripcion() {
        return "";
    }

    @Override
    public String getFoto() {
        return "";
    }

    @Override
    public int getCantidad() {
        return 0;
    }

    @Override
    public int getDescuento() {
        return 0;
    }

    @Override
    public Usuario getUsuario() {
        return null;
    }

    @Override
    public Categoria getCategoria() {
        return null;
    }

    @Override
    public Estados getEstado() {
        return null;
    }

    @Override
    public ArrayList<ValorAtributoProducto> getDatos() {
        return null;
    }

    @Override
    public ArrayList<Producto> getProductos() {
        return null;
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findByCategoria(Categoria categoria) {
        return Optional.empty();
    }

    @Override
    public void setNombre(String nombre) {

    }

    @Override
    public void setFecha(Date fecha) {

    }

    @Override
    public void setHora(LocalDateTime hora) {

    }

    @Override
    public void setValor(float valor) {

    }

    @Override
    public void setDescripcion(String descripcion) {

    }

    @Override
    public void setFoto(String foto) {

    }

    @Override
    public void setCantidad(int cantidad) {

    }

    @Override
    public void setUsuario(Usuario usuario) {

    }

    @Override
    public void setCategoria(Categoria categoria) {

    }

    @Override
    public void setEstado(Estados estado) {

    }
}
