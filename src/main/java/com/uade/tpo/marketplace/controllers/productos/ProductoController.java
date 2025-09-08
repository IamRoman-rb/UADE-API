package com.uade.tpo.marketplace.controllers.productos;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicadoException;
import com.uade.tpo.marketplace.exceptions.ProductoExistenteException;
import com.uade.tpo.marketplace.service.ProductoService;
import org.springframework.http.HttpStatus;
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

        if (!productoRequest.isValid()) {
            return ResponseEntity.badRequest().body("Atributo ID cannot be null in datos");
        }

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

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoriaId(@PathVariable String categoriaId) {
        List<Producto> productos = productoService.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(productos);
    }

    // ENDPOINT 2: Por objeto categoría en el body
    @PostMapping("/categoria")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@RequestBody Categoria categoria) {
        List<Producto> productos = productoService.findByCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String id, @RequestBody ProductoUpdateRequest producto) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof ProductoDuplicadoException) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un producto con ese nombre");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
    }
}