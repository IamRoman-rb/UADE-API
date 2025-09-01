package com.uade.tpo.marketplace.service.imp;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.controllers.compras.CompraRequest;
import com.uade.tpo.marketplace.exceptions.CompraNotFoundException;
import com.uade.tpo.marketplace.exceptions.UsuarioNotFoundException;
import com.uade.tpo.marketplace.repository.CompraRepository;
import com.uade.tpo.marketplace.service.CompraService;
import com.uade.tpo.marketplace.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraServiceImp implements CompraService {

    private final CompraRepository compraRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public CompraServiceImp(CompraRepository compraRepository, UsuarioService usuarioService) {
        this.compraRepository = compraRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Compra crearCompra(CompraRequest compraRequest) {
        Usuario usuario = usuarioService.findById(compraRequest.getUsuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + compraRequest.getUsuarioId()));

        Compra compra = new Compra();
        compra.setValor(compraRequest.getValor());
        compra.setUsuario(usuario);
        compra.setItems(compraRequest.getItems());
        compra.setFechaHora(compraRequest.getHora() != null ? compraRequest.getHora() : LocalDateTime.now());

        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    @Override
    public List<Compra> findAllByUsuario(Usuario usuario) {
        return compraRepository.findByUsuario(usuario);
    }

    @Override
    public Compra findById(String id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new CompraNotFoundException("Compra no encontrada con ID: " + id));
    }

    @Override
    public List<Compra> findByFecha(LocalDateTime fecha) {
        return compraRepository.findByFechaHoraBetween(fecha.toLocalDate().atStartOfDay(), fecha.toLocalDate().atTime(23,59,59));
    }

    @Override
    public void deleteCompra(String id) {
        if (!compraRepository.existsById(id)) {
            throw new CompraNotFoundException("Compra no encontrada con ID: " + id);
        }
        compraRepository.deleteById(id);
    }
}
