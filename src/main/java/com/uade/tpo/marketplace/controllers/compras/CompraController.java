package com.uade.tpo.marketplace.controllers.compras;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.exceptions.CompraNotFoundException;
import com.uade.tpo.marketplace.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Compra> crearCompra(@RequestBody CompraRequest compraRequest) {
        Compra nuevaCompra = compraService.crearCompra(compraRequest);
        return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAll();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable String id ) throws CompraNotFoundException {
        Compra compra = compraService.findById(id); // El servicio ya lanzará NotFoundException si no existe
        return ResponseEntity.ok(compra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable String id) throws CompraNotFoundException {
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }
}