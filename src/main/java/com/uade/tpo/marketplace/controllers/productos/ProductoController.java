package com.uade.tpo.marketplace.controllers.productos;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicadoException;
import com.uade.tpo.marketplace.exceptions.ProductoExistenteException;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearProducto(@RequestBody ProductoRequest productoRequest)
            throws ProductoDuplicadoException {
        Producto result = productoService.crearProducto(productoRequest);

        return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(result);

    }


    @GetMapping("/")
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> producto = productoService.getProductos();
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@RequestBody ProductoRequest request) {
        return productoService.actualizarProducto(request);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
    }
}