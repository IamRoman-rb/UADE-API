package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.controllers.productos.ProductoAtributoRequest;
import com.uade.tpo.marketplace.controllers.productos.ProductoRequest;
import com.uade.tpo.marketplace.entity.Atributo;
import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.ValorAtributo;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicadoException;
import com.uade.tpo.marketplace.repository.AtributoRepository;
import com.uade.tpo.marketplace.repository.ProductoRepository;
import com.uade.tpo.marketplace.repository.ValorAtributoProducto;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;
    private final AtributoRepository atributoRepository;

    public ProductoServiceImp(ProductoRepository productoRepository, ValorAtributoProducto valorAtributoProductoRepository, AtributoRepository atributoRepository) {
        this.productoRepository = productoRepository;
        this.atributoRepository = atributoRepository;
    }

    @Override
    public Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicadoException {
        System.out.println(productoRequest);

        // Verificar duplicado
        Optional<Producto> existente = productoRepository.findByNombreEqualsIgnoreCase(productoRequest.getNombre());
        if (existente.isPresent()) {
            throw new ProductoDuplicadoException();
        }

        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setCantidad(productoRequest.getCantidad());
        producto.setCategoria(productoRequest.getCategoria());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setFoto(productoRequest.getFoto());
        producto.setValor(productoRequest.getValor());
        producto.setDescuento(productoRequest.getDescuento());
        producto.setFechaHora(LocalDateTime.now());

        if (productoRequest.getDatos() != null && !productoRequest.getDatos().isEmpty()) {
            List<ValorAtributo> datosCompletos = new ArrayList<>();

            for (ProductoAtributoRequest datoRequest : productoRequest.getDatos()) {
                if (datoRequest.getAtributoNombre() == null || datoRequest.getValor() == null) {
                    throw new IllegalArgumentException("AtributoNombre y Valor no pueden ser null");
                }

                // Buscar el atributo por nombre
                Atributo atributo = atributoRepository.findByNombre(datoRequest.getAtributoNombre());
                if (atributo == null) {
                    // Crear el atributo si no existe
                    atributo = new Atributo();
                    atributo.setNombre(datoRequest.getAtributoNombre());
                    atributo = atributoRepository.save(atributo);
                }

                ValorAtributo datoCompleto = new ValorAtributo();
                datoCompleto.setValor(datoRequest.getValor());
                datoCompleto.setAtributo(atributo);
                datoCompleto.setProducto(producto);

                datosCompletos.add(datoCompleto);
            }

            producto.setDatos(datosCompletos);
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

    @Override
    public Producto actualizarProducto(ProductoRequest request) {
        return null;
    }
}
