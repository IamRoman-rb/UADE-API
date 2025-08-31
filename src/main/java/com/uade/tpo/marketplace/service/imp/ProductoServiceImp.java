package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicadoException;
import com.uade.tpo.marketplace.repository.ProductoRepository;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImp(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        if (productoRepository.findById(producto.getId()).isPresent()) {
            throw new RuntimeException("El producto ya existe");
        }
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> p.getCategoria().equals(categoria)).collect(Collectors.toList());
    }

    @Override
    public Producto actualizarProducto(String id, Producto producto) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setDescripcion(producto.getDescripcion());
                    p.setValor(producto.getValor());
                    p.setCantidad(producto.getCantidad());
                    p.setDescuento(producto.getDescuento());
                    p.setCategoria(producto.getCategoria());
                    p.setDatos(producto.getDatos());
                    return productoRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public void eliminarProducto(String id) {
        productoRepository.deleteById(id);
    }
}
