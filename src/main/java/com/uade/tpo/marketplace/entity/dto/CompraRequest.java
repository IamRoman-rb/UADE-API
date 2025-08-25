package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Item;
import com.uade.tpo.marketplace.entity.Usuario;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
public class CompraRequest {
    private String id;
    private float valor;
    private Usuario usuario;
    private ArrayList<Item> items;
    private Date fecha;
    private LocalDateTime hora;
}
