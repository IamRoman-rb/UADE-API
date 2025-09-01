package com.uade.tpo.marketplace.controllers.productos;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Producto crearProducto(@RequestBody ProductoRequest request) {
        Producto producto = new Producto(
                request.getNombre(),
                request.getValor(),
                request.getDescripcion(),
                request.getFoto(),
                request.getCantidad(),
                request.getDescuento(),
                request.getCategoria(),
                request.getDatos()
        );
        return productoService.crearProducto(producto);
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable String id, @RequestBody ProductoRequest request) {
        Producto producto = new Producto(
                request.getNombre(),
                request.getValor(),
                request.getDescripcion(),
                request.getFoto(),
                request.getCantidad(),
                request.getDescuento(),
                request.getCategoria(),
                request.getDatos()
        );
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
    }
}
