package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, String> {
    List<Compra> findByUsuario(Usuario usuario);

    List<Compra> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
