package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    Optional<List<Producto>> findByNameEqualsIgnoreCase(String nombre);
}
