package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Item;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class CompraRequest {
    private String id;

    private float valor;

    private String usuarioId;

    private ArrayList<Item> items;
    private LocalDateTime hora;
}
